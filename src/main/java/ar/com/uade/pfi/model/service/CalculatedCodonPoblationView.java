package ar.com.uade.pfi.model.service;

public class CalculatedCodonPoblationView {

	String codonName;
	Double calculatedPoblationLn;
	Double experimentalPoblationLn;
	Double energy;

	public CalculatedCodonPoblationView() { }

	public CalculatedCodonPoblationView(String codonName, Double calculatedPoblationLn, Double experimentalPoblationLn, Double energy) {
		this.codonName = codonName;
		this.calculatedPoblationLn = calculatedPoblationLn;
		this.experimentalPoblationLn = experimentalPoblationLn;
		this.energy = energy;
	}

	public String getCodonName() {
		return codonName;
	}

	public void setCodonName(String codonName) {
		this.codonName = codonName;
	}

	public Double getCalculatedPoblationLn() {
		return calculatedPoblationLn;
	}

	public void setCalculatedPoblationLn(Double calculatedPoblationLn) {
		this.calculatedPoblationLn = calculatedPoblationLn;
	}

	public Double getExperimentalPoblationLn() {
		return experimentalPoblationLn;
	}

	public void setExperimentalPoblationLn(Double experimentalPoblationLn) {
		this.experimentalPoblationLn = experimentalPoblationLn;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}
}
