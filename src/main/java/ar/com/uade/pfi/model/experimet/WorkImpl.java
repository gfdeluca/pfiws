package ar.com.uade.pfi.model.experimet;

import ar.com.uade.pfi.model.Codon;

public class WorkImpl {	
	Codon _origin, _destination;
	private double _wab,_lagrangeMult;
	
	public WorkImpl(Codon origin, Codon destination, double lagrangeMultiplier) {
		this._origin = origin;
		this._destination = destination;
		this._lagrangeMult = lagrangeMultiplier;
		this._wab = calculateWabWork(this._origin.getPa(), this._destination.getPa());
	}
	
	public double getWab() {
		return this._wab;
	}
	
	public void setWab(double wab) {
		this._wab = wab;
	}
	
	public void recalculate() {
		this._wab = calculateWabWork(this._origin.getPa(), this._destination.getPa());
	}
	
	private double calculateWabWork(double ea, double eb) {
		return Math.exp( (-1 * _lagrangeMult) * ( (ea + eb)/2 ) );
	}
}
