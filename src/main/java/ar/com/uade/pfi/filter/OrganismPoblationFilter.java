package ar.com.uade.pfi.filter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;

public interface OrganismPoblationFilter {
	@JsonIgnore
	Long getIdOrganimPoblation();

	@JsonIgnore
	OrganismEntity getIdOrganim();

	Double getGamma();

	Double getPearsonCoefficient();

	@JsonIgnore
	Date getLastUpdate();

	@JsonIgnore
	Long getIdTransition();
}