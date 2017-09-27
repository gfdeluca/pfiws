package ar.com.uade.pfi.dao.dbo.entities;

public class CalculatedCodonPoblationEntity {
	String codon;
	Double poblation;
	Double poblationLn;
	Double energy;

	public CalculatedCodonPoblationEntity() { }

	public CalculatedCodonPoblationEntity(String codon, Double poblation, Double poblationLn, Double energy) {
		this.codon = codon;
		this.poblation = poblation;
		this.poblationLn = poblationLn;
		this.energy = energy;
	}

	public String getCodon() {
		return codon;
	}

	public void setCodon(String codon) {
		this.codon = codon;
	}

	public Double getPoblation() {
		return poblation;
	}

	public void setPoblation(Double poblation) {
		this.poblation = poblation;
	}

	public Double getPoblationLn() {
		return poblationLn;
	}

	public void setPoblationLn(Double poblationLn) {
		this.poblationLn = poblationLn;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}
}
