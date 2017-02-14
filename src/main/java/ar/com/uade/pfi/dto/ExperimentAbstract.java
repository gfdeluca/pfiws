package ar.com.uade.pfi.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ejml.alg.dense.decomposition.eig.SymmetricQRAlgorithmDecomposition_D64;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.lang.Constants;
import ar.com.uade.pfi.model.Codon;
import ar.com.uade.pfi.model.experimet.StandardCodonImpl;
import ar.com.uade.pfi.model.experimet.WorkImpl;

public abstract class ExperimentAbstract implements Experiment {
	public abstract void populatePaMatrix() throws Exception;

	protected int _paMatrixSize = 0;
	protected double _gamma, _porcGC;
	protected DenseMatrix64F _matrix = null;
	protected List<CodonEntity> _codons = new ArrayList<CodonEntity>();
//	protected Map<CodonEntity, Double> _poblationEigVector = new HashMap<CodonEntity, Double>();
	protected Map<CodonEntity, Double> _poblationEigVector = new TreeMap<CodonEntity, Double>(
            new Comparator<CodonEntity>() {

                @Override
                public int compare(CodonEntity o1, CodonEntity o2) {
                    return o1.getId().compareTo(o2.getId());
                }

            }
    ); 
			
	protected Map<String, Double> _codonEnergies = new HashMap<String, Double>();
	protected Map<String, Codon> codonArray = new HashMap<String, Codon>();
	
	/*
	 * Utiliza el gamma por defecto de 0.01
	 */
	public ExperimentAbstract(List<CodonEntity> codons, double porcGC) {
		this._gamma = Constants.defaultLagrangeMultiplier;
		this._codons = codons;
		this._porcGC = porcGC;
		this._paMatrixSize = codons.size();
		this.preCalcCodons();
	}
	
	public ExperimentAbstract(List<CodonEntity> codons, double porcGC, double gamma) {
		this._gamma = gamma;
		this._codons = codons;
		this._porcGC = porcGC;
		this._paMatrixSize = codons.size();
		this.preCalcCodons();
	}
	
	protected void createPaMatrix() {
		_matrix = new DenseMatrix64F(_paMatrixSize, _paMatrixSize);
		int row = 0, column = 0;
//		Codon origin, destination;
		
		for (Codon origin : this.codonArray.values()) {
			this._codonEnergies.put(origin.toString(), origin.getPa());
			for (Codon destination : this.codonArray.values()) {
				if (origin.isNeighbour(destination)) {
					WorkImpl w = new WorkImpl(origin, destination, _gamma);
					_matrix.add(row, column, w.getWab());
				} else {
					_matrix.add(row, column, 0);
				}
				column++;
			}
			column = 0;
			row++;
		}
		
//		for (CodonEntity codon1 : this._codons) {
//			origin = new StandardCodonImpl(codon1, this._porcGC);
//			this._codonEnergies.put(codon1.getName(), origin.getPa());
//			for (CodonEntity codon2 : this._codons) {
//				destination = new StandardCodonImpl(codon2, this._porcGC);
//				if (origin.isNeighbour(destination)) {
//					WorkImpl w = new WorkImpl(origin, destination, _gamma);
//					_matrix.add(row, column, w.getWab());
//				} else {
//					_matrix.add(row, column, 0);
//				}
//				column++;
//			}
//			column = 0;
//			row++;
//		}
	}
	
	protected void preCalcCodons() {
		for (CodonEntity codon : this._codons) {
			codonArray.put(codon.getName(), new StandardCodonImpl(codon, this._porcGC));
		}
	}
	
	protected void calculateEigenVector() {
		DenseMatrix64F auxMatrix = _matrix.copy();
		SymmetricQRAlgorithmDecomposition_D64 eig 	= new SymmetricQRAlgorithmDecomposition_D64(true);
		eig.decompose(auxMatrix);
		
		DenseMatrix64F eigVector = eig.getEigenVector(0);
		DenseMatrix64F aux = new DenseMatrix64F(eigVector.getNumRows(), eigVector.getNumCols());
		CommonOps.elementPower(eigVector, 2, aux);
		eigVector = aux.copy();
		
		int i = 0;
		for (CodonEntity codon : this._codons) {
			_poblationEigVector.put(codon, eigVector.get(i++));			
		}
	}

	@Override
	public DenseMatrix64F getEnergyMatrix() {
		return _matrix;
	}

	@Override
	public Map<CodonEntity, Double> getPoblationEigenVector() {
		return _poblationEigVector;
	}
	
	@Override
	public Map<String, Double> getCodonEnergies() {
		return _codonEnergies;
	}

	protected void logMatrixs() {	
//		logger.info("Experimento: {}", this.getExperimentTitle());
//		logger.info("Matriz de energia");
//		logger.info(this.getEnergyMatrix());
//		logger.info("Vector poblacional");
//		logger.info(this.getPoblationEigenVector());
//		logger.info("-----------------------------------------------------------------------------------------");
	}

}