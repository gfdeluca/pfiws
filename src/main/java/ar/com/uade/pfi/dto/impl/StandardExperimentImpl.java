package ar.com.uade.pfi.dto.impl;

import java.util.List;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dto.Experiment;
import ar.com.uade.pfi.dto.ExperimentAbstract;

public class StandardExperimentImpl extends ExperimentAbstract implements Experiment {
	public StandardExperimentImpl(List<CodonEntity> codons, double porcGC) {
		super(codons, porcGC);
	}
	
	public StandardExperimentImpl(List<CodonEntity> codons, double porcGC, double gamma) {
		super(codons, porcGC, gamma);
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.dto.impl.Experimenta#populatePaMatrix()
	 */
	@Override
	public void populatePaMatrix() throws Exception {
		createPaMatrix();
		calculateEigenVector();
		logMatrixs();
	}

	@Override
	public String getExperimentTitle() {
		return "Experimento standard";
	}
	
	@Override
	public String getID() {
		return "standard";
	}
}
