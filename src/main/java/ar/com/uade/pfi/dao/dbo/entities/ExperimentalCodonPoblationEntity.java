package ar.com.uade.pfi.dao.dbo.entities;

public class ExperimentalCodonPoblationEntity {
	Long idExperimentalCodonPoblation;
	String codon;
	Double poblation;
	Double poblationLn;
	
	public ExperimentalCodonPoblationEntity() { }

	public ExperimentalCodonPoblationEntity(Long idExperimentalCodonPoblation, String codon, Double poblation,
			Double poblationLn) {
		this.idExperimentalCodonPoblation = idExperimentalCodonPoblation;
		this.codon = codon;
		this.poblation = poblation;
		this.poblationLn = poblationLn;
	}

	public Long getIdExperimentalCodonPoblation() {
		return idExperimentalCodonPoblation;
	}

	public void setIdExperimentalCodonPoblation(Long idExperimentalCodonPoblation) {
		this.idExperimentalCodonPoblation = idExperimentalCodonPoblation;
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
}
