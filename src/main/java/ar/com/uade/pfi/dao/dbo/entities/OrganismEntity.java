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
@Table(name = "Organisms")
public class OrganismEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOrganism")
	Long idOrganism;

	@Column(name = "name", length = 255)
	String name;

	@Column(name = "porcGC", length = 13, precision = 12)
	Double porcGC;

//	@OneToOne
//	@JoinColumn(name = "bestFitPoblation", referencedColumnName = "idOrganismPoblation", nullable = true)
//	OrganismPoblationEntity bestFitPoblation;
	@Column(name = "bestPearson", length = 6, precision = 5)
	Double bestPearson;

	@Column(name = "lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	Date lastUpdate;
	
	public OrganismEntity() {}
	
	public OrganismEntity(Long idOrganism, String name, Double porcGC, Double bestPearson, Date lastUpdate) {
		this.idOrganism = idOrganism;
		this.name = name;
		this.porcGC = porcGC;
		this.bestPearson = bestPearson;
		this.lastUpdate = lastUpdate;
	}

	public Long getIdOrganism() {
		return idOrganism;
	}

	public void setIdOrganism(Long idOrganism) {
		this.idOrganism = idOrganism;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPorcGC() {
		return porcGC;
	}

	public void setPorcGC(Double porcGC) {
		this.porcGC = porcGC;
	}

	public Double getBestPearson() {
		return bestPearson;
	}

	public void setBestPearson(Double bestPearson) {
		this.bestPearson = bestPearson;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
