package ar.com.uade.pfi.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CalculatedCodonPoblationFilter {

	@JsonIgnore
	Long getIdCalculatedCodonPoblation();

	@JsonIgnore
	Long getIdOrganimPoblation();

	@JsonIgnore
	Long getIdCodon();

	@JsonIgnore
	Double getPoblation();

	Double getPoblationLn();

	Double getEnergy();

}