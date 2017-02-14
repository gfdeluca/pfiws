package ar.com.uade.pfi.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;

public interface ExperimentalCodonPoblationFilter {
	Long getIdExperimentalCodonPoblation();
	@JsonIgnore
	OrganismEntity getIdOrganim();
	CodonEntity getIdCodon();
	Double getPoblation();
	Double getPoblationLn();
}