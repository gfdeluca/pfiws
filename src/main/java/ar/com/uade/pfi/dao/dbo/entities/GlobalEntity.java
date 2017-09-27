package ar.com.uade.pfi.dao.dbo.entities;

import java.util.Date;

public class GlobalEntity {
	Long idGlobal;
	Double bestPearsonCoefficient;
	Integer lasRunOk;
	Date lastUpdate;

	public GlobalEntity(Long idGlobal, Double bestPearsonCoefficient, Date lastUpdate, Integer lastRunOk) {
		this.idGlobal = idGlobal;
		this.bestPearsonCoefficient = bestPearsonCoefficient;
		this.lastUpdate = lastUpdate;
		this.lasRunOk = lastRunOk;
	}

	public GlobalEntity() {
	}

	public Long getId() {
		return idGlobal;
	}

	public void setId(Long idGlobal) {
		this.idGlobal = idGlobal;
	}

	public Double getBestPearsonCoefficient() {
		return bestPearsonCoefficient;
	}

	public void setBestPearsonCoefficient(Double bestPearsonCoefficient) {
		this.bestPearsonCoefficient = bestPearsonCoefficient;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getLasRunOk() {
		return lasRunOk;
	}

	public void setLasRunOk(Integer lasRunOk) {
		this.lasRunOk = lasRunOk;
	}
	
	
}
