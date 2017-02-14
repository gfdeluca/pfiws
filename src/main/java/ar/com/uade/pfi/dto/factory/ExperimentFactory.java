package ar.com.uade.pfi.dto.factory;

import java.util.List;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dto.Experiment;
import ar.com.uade.pfi.dto.impl.StandardExperimentImpl;

public class ExperimentFactory {
	
	public static int STANDARD_EXPERIMENT = 1;
	
	public static Experiment getExperiment(int experimentType, List<CodonEntity> codons, double porcGC, double gamma) throws Exception{
		switch (experimentType) {
		case 1:
			return new StandardExperimentImpl(codons, porcGC, gamma);
		default:
			throw new Exception("No existe el experimiento seleccionado");
		}
	}
}
