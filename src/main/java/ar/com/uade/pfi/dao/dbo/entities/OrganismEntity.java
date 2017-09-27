package ar.com.uade.pfi.dao.dbo.entities;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class OrganismEntity {
	ObjectId id;
	String name;
	Double porcGC;
	Double bestPearson;
	Date lastUpdate;
	List<ExperimentalCodonPoblationEntity> experimentalValues;
	List<OrganismPoblationEntity> poblation;
	
	public OrganismEntity() {}

	public OrganismEntity(ObjectId idOrganism, String name, Double porcGC, Double bestPearson, Date lastUpdate,
			List<ExperimentalCodonPoblationEntity> experimentalValues) {
		this.id = idOrganism;
		this.name = name;
		this.porcGC = porcGC;
		this.bestPearson = bestPearson;
		this.lastUpdate = lastUpdate;
		this.experimentalValues = experimentalValues;
	}
	
	public OrganismEntity(ObjectId idOrganism, String name, Double porcGC, Double bestPearson, Date lastUpdate,
			List<ExperimentalCodonPoblationEntity> experimentalValues, List<OrganismPoblationEntity> poblation) {
		this.id = idOrganism;
		this.name = name;
		this.porcGC = porcGC;
		this.bestPearson = bestPearson;
		this.lastUpdate = lastUpdate;
		this.experimentalValues = experimentalValues;
		this.poblation = poblation;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId idOrganism) {
		this.id= idOrganism;
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

	public List<ExperimentalCodonPoblationEntity> getExperimentalValues() {
		return experimentalValues;
	}

	public void setExperimentalValues(List<ExperimentalCodonPoblationEntity> experimentalValues) {
		this.experimentalValues = experimentalValues;
	}

	public List<OrganismPoblationEntity> getPoblation() {
		return poblation;
	}

	public void setPoblation(List<OrganismPoblationEntity> poblation) {
		this.poblation = poblation;
	}

	
}
