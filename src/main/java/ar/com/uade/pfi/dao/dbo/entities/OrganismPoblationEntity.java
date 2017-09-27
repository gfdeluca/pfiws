package ar.com.uade.pfi.dao.dbo.entities;

import java.util.Date;
import java.util.List;

public class OrganismPoblationEntity {
	Double gamma;
	Double pearsonCoefficient;
	Date lastUpdate;
	List<CalculatedCodonPoblationEntity> calculatedCodon;
	
	public OrganismPoblationEntity() {}

	public OrganismPoblationEntity(Double gamma, Double pearsonCoefficient, Date lastUpdate,
			List<CalculatedCodonPoblationEntity> calculatedCodon) {
		this.gamma = gamma;
		this.pearsonCoefficient = pearsonCoefficient;
		this.lastUpdate = lastUpdate;
		this.calculatedCodon = calculatedCodon;
	}

	public Double getGamma() {
		return gamma;
	}

	public void setGamma(Double gamma) {
		this.gamma = gamma;
	}

	public Double getPearsonCoefficient() {
		return pearsonCoefficient;
	}

	public void setPearsonCoefficient(Double pearsonCoefficient) {
		this.pearsonCoefficient = pearsonCoefficient;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<CalculatedCodonPoblationEntity> getCalculatedCodon() {
		return calculatedCodon;
	}

	public void setCalculatedCodon(List<CalculatedCodonPoblationEntity> calculatedCodon) {
		this.calculatedCodon = calculatedCodon;
	}
}
