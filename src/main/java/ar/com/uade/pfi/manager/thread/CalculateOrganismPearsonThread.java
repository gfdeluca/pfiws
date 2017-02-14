package ar.com.uade.pfi.manager.thread;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

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

public class CalculateOrganismPearsonThread implements Runnable {
	private final OrganismEntity _organism;
	private final double _gamma;
	private final List<CodonEntity> _codons;
	
	public CalculateOrganismPearsonThread(OrganismEntity organism, double gamma, List<CodonEntity> codons) {
		this._organism = organism;
		this._gamma = gamma;
		this._codons = codons;
	}
	
	
	@Override
	public void run() {
		// Creo el experimento
		Experiment e;
		try {
			e = ExperimentFactory.getExperiment(ExperimentFactory.STANDARD_EXPERIMENT, this._codons,
					this._organism.getPorcGC(), _gamma);
			e.populatePaMatrix();
		} catch (Exception e2) {
			// Ante un error de calculo anulo el calculo de este gamma
			return;
		}

				
		// Calculo las poblaciones y obtengo el vector con los resultados asociados a cada codon

		//TODO MEJORAR ACA!!! Ya lo deberia recuperar ordenados
		Map<CodonEntity, Double> eig = new TreeMap<CodonEntity, Double>(
                new Comparator<CodonEntity>() {

                    @Override
                    public int compare(CodonEntity o1, CodonEntity o2) {
                        return o1.getId().compareTo(o2.getId());
                    }

                });
		eig.putAll(e.getPoblationEigenVector());

		// Creo la nueva entrada en la tabla de poblaciones del organismo
		OrganismPoblationEntity ope = new OrganismPoblationEntity(this._organism, _gamma, 0.0, new Date(),
				new Long(0));
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
			return;
		}
		

		// Recupero las poblaciones experimentales del organismo
		List<ExperimentalCodonPoblationEntity> ecps = ExperimentalCodonPoblationDao
				.getAllByOrganism(this._organism);

		// Transformo las poblaciones experimentales en un array simple para poder calcular el R de pearson
		double[] arrEcp = ArrayUtils.poblationsLnToArray(ecps);

		PearsonsCorrelation pc = new PearsonsCorrelation();
		double pearsonValue = pc.correlation(arrEcp, arrCcpe);
		
		ope.setPearsonCoefficient(pearsonValue);;
		DBManager.getInstance().Update(ope);
		
		ProcessingManager.getInstance().incrementProgress();
		return;
	}

}
