package ar.com.uade.pfi.model.service;

import java.util.List;

import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.filter.OrganismPoblationFilter;

public class OrganismsPoblationsServiceModel {
	OrganismEntity organism;
	List<OrganismPoblationFilter> organismsPoblations;
	public OrganismEntity getOrganism() {
		return organism;
	}
	public void setOrganism(OrganismEntity organism) {
		this.organism = organism;
	}
	public List<OrganismPoblationFilter> getOrganismsPoblations() {
		return organismsPoblations;
	}
	public void setOrganismsPoblations(List<OrganismPoblationFilter> ope) {
		this.organismsPoblations = ope;
	}
}
