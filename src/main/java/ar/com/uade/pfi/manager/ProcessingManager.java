package ar.com.uade.pfi.manager;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ar.com.uade.pfi.dao.CalculatedCodonPoblationDao;
import ar.com.uade.pfi.dao.OrganismPoblationDao;
import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.manager.thread.CalculateOrganismGammaThread;
import ar.com.uade.pfi.model.experimet.StatusServiceModel;

public class ProcessingManager {
	private static ProcessingManager _instance = null;
	private static boolean _lock = false;
	private static Integer _total = 0, _progress = 0;
	private static final int NTHREDS = 900;

	private ProcessingManager() {
	}

	public static ProcessingManager getInstance() {
		if (_instance == null) {
			_instance = new ProcessingManager();
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
		if (_lock) {
			return false;
		} else {
			_lock = true;
		}

		try {
			//Creo una sesion con transaccion para procesar este thead
			
			CalculatedCodonPoblationDao.truncate();
			OrganismPoblationDao.truncate();
			
			List<CodonEntity> codons = DBManager.getInstance().getAll(CodonEntity.class);
			List<OrganismEntity> organims = DBManager.getInstance().getAll(OrganismEntity.class);
//			AppConfigurationEntity appConfGammas = AppConfigurationDao.get("process.gammas");
//			List<Double> gammas = ArrayUtils.arrayOFStringToDouble(appConfGammas.getValor().split(","));
			int gammaSize = 100000;
			
			_total = organims.size() * gammaSize;
			ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
			
			for (Iterator<OrganismEntity> iterator = organims.iterator(); iterator.hasNext();) {
				OrganismEntity organism = iterator.next();
				
				//Creo la variable para saber cual es gamma con mejor R
//				double bestPearson = 0, bestOrganism = 0;
				
				// Itero por cada gamma pre fijado
//				BigDecimal value = BigDecimal.ZERO;
//				double gamma = 0;
//				
//				for(int i = 0; i <= gammaSize; i++) {
//					value = value.add(BigDecimal.valueOf(0.001d));
//					gamma = value.doubleValue();
//	
//					Runnable worker = new CalculateOrganismPearsonThread(organism, gamma, codons);
//					executor.execute(worker);
//				}
				
				CalculateOrganismGammaThread cogt = new CalculateOrganismGammaThread(organism, gammaSize, codons);
				executor.submit(cogt);
				
			}
			
			executor.shutdown();
			try {
				  executor.awaitTermination(2, TimeUnit.DAYS);
				} catch (InterruptedException e) {
				  e.printStackTrace();
				}
			//TODO Busco el mejor gamma entre todos los organismos
			
		} catch (Exception e) {
			System.err.println(e);
			throw new Exception(e);
		} finally {
			_lock = false;
			_progress = 0;
			_total = 0;
		}

		return true;
	}
}
