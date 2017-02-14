package ar.com.uade.pfi.manager.thread;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.hibernate.Session;

import ar.com.uade.pfi.dao.CalculatedCodonPoblationDao;
import ar.com.uade.pfi.dao.ExperimentalCodonPoblationDao;
import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.CalculatedCodonPoblationEntity;
import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dao.dbo.entities.ExperimentalCodonPoblationEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationEntity;
import ar.com.uade.pfi.dto.Experiment;
import ar.com.uade.pfi.dto.factory.ExperimentFactory;
import ar.com.uade.pfi.manager.ProcessingManager;
import ar.com.uade.pfi.utils.ArrayUtils;

public class CalculateOrganismGammaThread implements Runnable {
	private final OrganismEntity _organism;
	private final int _gammas;
	private final List<CodonEntity> _codons;
	
	public CalculateOrganismGammaThread(OrganismEntity organism, int gammas, List<CodonEntity> codons) {
		this._organism = organism;
		this._gammas = gammas;
		this._codons = codons;
	}
	
//	@Override
//	public void run() {
//		//Creo la variable para saber cual es gamma con mejor R
//		double bestPearson = 0, bestOrganism = 0;
//		
//		// Itero por cada gamma pre fijado
//		BigDecimal value = BigDecimal.ZERO;
//		double gamma = 0;
//		
//		ExecutorService executor = Executors.newFixedThreadPool(150);
//        List<Future<OrganismPoblationEntity>> list = new ArrayList<Future<OrganismPoblationEntity>>();
//        
//		for(int i = 0; i <= _gammas; i++) {
//			value = value.add(BigDecimal.valueOf(0.001d));
//			gamma = value.doubleValue();
//			
//			CalculateOrganismPearsonThread copt = new CalculateOrganismPearsonThread(_organism, gamma, _codons);
////			Future<OrganismPoblationEntity> future = executor.submit(copt);
////            list.add(future);
//		}
//		
//		for(Future<OrganismPoblationEntity> fut : list){
//			OrganismPoblationEntity ope = null;
//			try {
//				ope = fut.get();
//			} catch (InterruptedException | ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (ope != null && bestPearson < Math.abs(ope.getPearsonCoefficient())) {
//				bestPearson = ope.getPearsonCoefficient();
//				bestOrganism = ope.getIdOrganim().getIdOrganism();
//			}
//			
//			ProcessingManager.getInstance().incrementProgress();
//        }
//        //shut down the executor service now
//        executor.shutdown();
//		
//		this._organism.setBestPearson(bestPearson);
//		DBManager.getInstance().Update(this._organism);
//
//	}
	
	@Override
	public void run() {
		//Creo la variable para saber cual es gamma con mejor R
		double bestPearson = 0, bestOrganism = 0;
		
		// Itero por cada gamma pre fijado
		BigDecimal value = BigDecimal.ZERO;
		double gamma = 0;
		
		//Creo una sesion con transaccion para procesar este thead
//		Session session = DBManager.getInstance().getSessionWithTransaction();
		
		for(int i = 0; i <= _gammas; i++) {
			value = value.add(BigDecimal.valueOf(0.001d));
			gamma = value.doubleValue();
			// Creo el experimento
			Experiment e;
			try {
				e = ExperimentFactory.getExperiment(ExperimentFactory.STANDARD_EXPERIMENT, this._codons,
						this._organism.getPorcGC(), gamma);
				e.populatePaMatrix();
			} catch (Exception e2) {
				// Ante un error de calculo anulo el calculo de este gamma
				ProcessingManager.getInstance().incrementProgress();
				
				//Cierro la conexion
//				DBManager.getInstance().closeSessionWithTransaction(session);
				
				continue;
			}

			Map<CodonEntity, Double> eig = e.getPoblationEigenVector();
			
			// Creo la nueva entrada en la tabla de poblaciones del organismo
			OrganismPoblationEntity ope = new OrganismPoblationEntity(this._organism, gamma, 0.0, new Date(), new Long(0));
			ope = DBManager.getInstance().Insert(ope);

			// Creo el array de las poblaciones calculadas para este gamma que lo voy a utilizar para calcular el R de pearson
			int pos = 0;
			double[] arrCcpe = new double[eig.size()];

			try {
				// Creo los entities a insertar, las asociaciones a los codones queda implicita por
				for (Map.Entry<CodonEntity, Double> entry : eig.entrySet()) {
					CalculatedCodonPoblationEntity ccpe = new CalculatedCodonPoblationEntity();
					ccpe.setIdOrganimPoblation(ope);
					ccpe.setIdCodon(entry.getKey());
					ccpe.setPoblation(entry.getValue());
					ccpe.setPoblationLn(Math.log(entry.getValue()));
					ccpe.setEnergy(e.getCodonEnergies().get(entry.getKey().getName()));
					DBManager.getInstance().Insert(ccpe);

					arrCcpe[pos++] = ccpe.getPoblationLn();
				}
			} catch (Exception e1) {
				// Ante un error de calculo anulo el calculo de este gamma
				CalculatedCodonPoblationDao.deleteByOrganismPoblation(ope.getIdOrganimPoblation());
				DBManager.getInstance().delete(ope);
				ProcessingManager.getInstance().incrementProgress();
				
				//Cierro la conexion
//				DBManager.getInstance().closeSessionWithTransaction(session);
				
				continue;
			}
			

			// Recupero las poblaciones experimentales del organismo
			List<ExperimentalCodonPoblationEntity> ecps = ExperimentalCodonPoblationDao.getAllByOrganism(this._organism);

			// Transformo las poblaciones experimentales en un array simple para poder calcular el R de pearson
			double[] arrEcp = ArrayUtils.poblationsLnToArray(ecps);

			PearsonsCorrelation pc = new PearsonsCorrelation();
			double pearsonValue = pc.correlation(arrEcp, arrCcpe);
			
			ope.setPearsonCoefficient(pearsonValue);;
			DBManager.getInstance().Update(ope);
			
			if (bestPearson < Math.abs(ope.getPearsonCoefficient())) {
				bestPearson = ope.getPearsonCoefficient();
				bestOrganism = ope.getIdOrganim().getIdOrganism();
			}
			
			ProcessingManager.getInstance().incrementProgress();
//			Thread.sleep(1000);
		}
		
		this._organism.setBestPearson(bestPearson);
		DBManager.getInstance().Update(this._organism);
		
//		DBManager.getInstance().closeSessionWithTransaction(session);
	}

}
