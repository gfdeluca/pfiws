package ar.com.uade.pfi.model;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;

public abstract class CodonAbstract implements Codon {
	private char _fisrtNucleotide;
	private char _secondNucleotide;
	private char _thirdNucleotide;
	private double _porcGC, _pa, _PcPg, _PaPt;
	
	public CodonAbstract() { }
	
	public CodonAbstract(CodonEntity codon, double porcGC) {		
		this._fisrtNucleotide = codon.getName().charAt(0);
		this._secondNucleotide = codon.getName().charAt(1);
		this._thirdNucleotide = codon.getName().charAt(2);
		
		this._porcGC = porcGC;
		this.calculateCodonEnergy();
		_pa = this.calculatePa();
	}
	
	protected double calculatePa() {
		double e = 1;
		if (this._fisrtNucleotide == 'C' || this._fisrtNucleotide == 'G') {
			e *= this._PcPg;
		} else {
			e *= this._PaPt;
		}

		if (this._secondNucleotide == 'C' || this._secondNucleotide == 'G') {
			e *= this._PcPg;
		} else {
			e *= this._PaPt;
		}
		
		if (this._thirdNucleotide == 'C' || this._thirdNucleotide == 'G') {
			e *= this._PcPg;
		} else {
			e *= this._PaPt;
		}
		
		return -1 * Math.log(e);
	}
	
	protected void calculateCodonEnergy() {
		_PaPt = (1 - _porcGC)/2;
		_PcPg = _porcGC/2;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#recalculate()
	 */
	private void recalculate() {
		this.calculateCodonEnergy();
		_pa = calculatePa();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#isNeighbour(ar.com.uade.pfi.model.experimet.Codon)
	 */
	public boolean isNeighbour(Codon c) {
		int countChanges = 0;
		if (this._fisrtNucleotide != c.getFisrtNucleotide()) {
			countChanges++;
		}
		
		if (this._secondNucleotide != c.getSecondNucleotide()) {
			countChanges++;
		}
		
		if (this._thirdNucleotide != c.getThirdNucleotide()) {
			countChanges++;
		}
		return countChanges <= 1;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#toString()
	 */
	@Override
	public String toString() {
		return "" + this._fisrtNucleotide + this._secondNucleotide + this._thirdNucleotide;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#getFisrtNucleotide()
	 */
	public char getFisrtNucleotide() {
		return _fisrtNucleotide;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#setFisrtNucleotide(char)
	 */
	public void setFisrtNucleotide(char fisrtNucleotide) {
		this._fisrtNucleotide = fisrtNucleotide;
		recalculate();
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#getSecondNucleotide()
	 */
	public char getSecondNucleotide() {
		return _secondNucleotide;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#setSecondNucleotide(char)
	 */
	public void setSecondNucleotide(char secondNucleotide) {
		this._secondNucleotide = secondNucleotide;
		recalculate();
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#getThirdNucleotide()
	 */
	public char getThirdNucleotide() {
		return _thirdNucleotide;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#setThirdNucleotide(char)
	 */
	public void setThirdNucleotide(char thirdNucleotide) {
		this._thirdNucleotide = thirdNucleotide;
		recalculate();
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#getPa()
	 */
	public double getPa() {
		return _pa;
	}

	/* (non-Javadoc)
	 * @see ar.com.uade.pfi.model.experimet.Codon#setPa(double)
	 */
	public void setPa(double pa) {
		this._pa = pa;
	}

	public double getPorcGC() {
		return _porcGC;
	}

	public void setPorcGC(double _porcGC) {
		this._porcGC = _porcGC;
		this.recalculate();
	}
}
