package ar.com.uade.pfi.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.uade.pfi.dao.AppConfigurationDao;
import ar.com.uade.pfi.dao.CodonsDao;
import ar.com.uade.pfi.dao.OrganismDao;
import ar.com.uade.pfi.dao.dbo.entities.AppConfigurationEntity;
import ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationEntity;
import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationEntity;
import ar.com.uade.pfi.dto.Experiment;
import ar.com.uade.pfi.dto.factory.ExperimentFactory;
import ar.com.uade.pfi.model.experimet.StatusServiceModel;
import ar.com.uade.pfi.utils.DateUtils;

public class ProcessingManagerV2 {
	private static final Logger logger = LogManager.getLogger(ProcessingManagerV2.class);
	
	private static ProcessingManagerV2 _instance = null;
	private static boolean _lock = false;
	private static Integer _total = 0, _progress = 0;

	private ProcessingManagerV2() {
	}

	public static void main(String[] args) {
		try {
			ProcessingManagerV2.getInstance().process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ProcessingManagerV2 getInstance() {
		if (_instance == null) {
			_instance = new ProcessingManagerV2();
		}
		return _instance;
	}

	public StatusServiceModel getStatus() {
		
		StatusServiceModel ssm = new StatusServiceModel();
		ssm.setLock(_lock);
		ssm.setProgress(_progress);
		ssm.setTotal(_total);
				
		return ssm;
	}

	public void incrementProgress() {
		_progress++;
	}
	
	public boolean process() throws Exception {
		long startTime = System.nanoTime();
		logger.info("########################################################################");
		logger.info("Arrancado a procesar el nuevo lote - {}", new Date());
		  
		if (_lock) {
			return false;
		} else {
			_lock = true;
		}

		try {
			logger.info("Recuperando la cantidad de gammas a procesasr y el salto entre cada uno");
			AppConfigurationEntity cfgProcessGamma = AppConfigurationDao.get("process.gammas");
			AppConfigurationEntity cfgProcessStep = AppConfigurationDao.get("process.step");
			double step = Double.parseDouble(cfgProcessStep.getValor());
			
			List<CodonEntity> codons = CodonsDao.getAll();
			List<OrganismEntity> organims = OrganismDao.getAll();
			
			String now = DateUtils.getTodayString();
						
			int gammaSize = Integer.parseInt(cfgProcessGamma.getValor());
			
			_total = organims.size() * gammaSize;
			logger.info("Comenzando a iterar sobre los organismos");
			for (Iterator<OrganismEntity> iterator = organims.iterator(); iterator.hasNext();) {
				OrganismEntity organism = iterator.next();

				BigDecimal value = BigDecimal.ZERO;
				double gamma = 0, bestPearson = 0;
				List<OrganismPoblationEntity> arrOpe = new ArrayList<OrganismPoblationEntity>();
				
				for(int i = 0; i <= gammaSize; i++) {
					value = value.add(BigDecimal.valueOf(step));
					gamma = value.doubleValue();
					
					// Creo el experimento
					Experiment e;
					try {
						e = ExperimentFactory.getExperiment(ExperimentFactory.STANDARD_EXPERIMENT, codons, organism.getPorcGC(), gamma);
						e.populatePaMatrix();
					} catch (Exception e2) {
						logger.error("Error al realizar el calculo de los autovectores del organismo {}", organism.getName(), e2);
						// Ante un error de calculo anulo el calculo de este gamma
						ProcessingManagerV2.getInstance().incrementProgress();
						continue;
					}

					Map<CodonEntity, Double> eig = e.getPoblationEigenVector();
					
					// Creo la nueva entrada en la tabla de poblaciones del organismo
					OrganismPoblationEntity ope = new OrganismPoblationEntity();
					ope.setGamma(gamma);
					ope.setLastUpdate(new Date());
					
					// Creo el array de las poblaciones calculadas para este gamma que lo voy a utilizar para calcular el R de pearson
					int pos = 0;
					double[] vecCcpe = new double[eig.size()];

					List<CalculatedCodonPoblationEntity> arrCcpe = new ArrayList<CalculatedCodonPoblationEntity>();
					try {
						// Creo los entities a insertar, las asociaciones a los codones queda implicita por
						for (Map.Entry<CodonEntity, Double> entry : eig.entrySet()) {
							CalculatedCodonPoblationEntity ccpe = new CalculatedCodonPoblationEntity();
							ccpe.setCodon(entry.getKey().getName());
							ccpe.setPoblation(entry.getValue());
							ccpe.setPoblationLn(Math.log(entry.getValue()));
							ccpe.setEnergy(e.getCodonEnergies().get(entry.getKey().getName()));
							
							arrCcpe.add(ccpe);
							vecCcpe[pos++] = ccpe.getPoblationLn();
						}
					} catch (Exception e1) {
						logger.error("Error al calcular la poblacion de codones del organismo {}", organism.getName(), e1);
						ProcessingManagerV2.getInstance().incrementProgress();
						continue;
					}
					
					ope.setCalculatedCodon(arrCcpe);
					
					PearsonsCorrelation pc = new PearsonsCorrelation();
//					double pearsonValue = pc.correlation(ecps.get(organism.getIdOrganism()), arrCcpe);
					
//					ope.setPearsonCoefficient(pearsonValue);
//					if (bestPearson < pearsonValue) {
//						bestPearson = pearsonValue;
//					}
					
					arrOpe.add(ope);
					ProcessingManagerV2.getInstance().incrementProgress();
				}
				
				organism.setPoblation(arrOpe);
//				OrganismDao.updateBestPearson(organism.getIdOrganism(), bestPearson);
				OrganismDao.updateOrganismPoblation(organism);
				
			}
			
			logger.info("Insertando los valores en CalculatedCodonsPoblations - OK");

		} catch (Exception e) {
			logger.error("Ocurrio un error general, frenando el proceso", e);
			throw new Exception(e);
		} finally {
			logger.info("Finalizando y cerrando conexiones");
			_lock = false;
			_progress = 0;
			_total = 0;
			
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			int seconds = (int) (duration / 1000) % 60 ;
			int minutes = (int) ((duration / (1000*60)) % 60);
			int hours   = (int) ((duration / (1000*60*60)) % 24);
			logger.info("Tiempos de ejecucion = {}hr, {}min, {}segs", hours, minutes, seconds);
		}

		return true;
	}
}
