package ar.com.uade.pfi.dao.dbo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Global")
public class GlobalEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idGlobal")
	Long idGlobal;

	@Column(name = "bestPearsonCoefficient", length = 6, precision = 5)
	Double bestPearsonCoefficient;
	
	@Column(name = "lastRunOk", length = 1)
	Integer lasRunOk;
	
	@Column(name = "lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
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
