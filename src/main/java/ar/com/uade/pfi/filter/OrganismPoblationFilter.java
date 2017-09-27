
package ar.com.uade.pfi.filter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface OrganismPoblationFilter {
	@JsonIgnore
	Long getIdOrganimPoblation();

	@JsonIgnore
	Long getIdOrganim();

	Double getGamma();

	Double getPearsonCoefficient();

	@JsonIgnore
	Date getLastUpdate();

	@JsonIgnore
	Long getIdTransition();
}