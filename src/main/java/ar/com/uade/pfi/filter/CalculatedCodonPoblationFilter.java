package ar.com.uade.pfi.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationEntity;

public interface CalculatedCodonPoblationFilter {

	Long getIdCalculatedCodonPoblation();

	@JsonIgnore
	OrganismPoblationEntity getIdOrganimPoblation();

	CodonEntity getIdCodon();

	Double getPoblation();

	Double getPoblationLn();

	Double getEnergy();

}