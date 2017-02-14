package ar.com.uade.pfi.dto;

import java.util.Map;

import org.ejml.data.DenseMatrix64F;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;

public interface Experiment {
	String getID();
	
	String getExperimentTitle();
	
	void populatePaMatrix() throws Exception;

	DenseMatrix64F getEnergyMatrix();

	Map<CodonEntity, Double> getPoblationEigenVector();
	
	Map<String, Double> getCodonEnergies();
}