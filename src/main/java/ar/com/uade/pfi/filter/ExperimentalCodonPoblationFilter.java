package ar.com.uade.pfi.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ExperimentalCodonPoblationFilter {
	Long getIdExperimentalCodonPoblation();
	@JsonIgnore
	Long getIdOrganim();
	Long getIdCodon();
	@JsonIgnore
	Double getPoblation();
	Double getPoblationLn();
}