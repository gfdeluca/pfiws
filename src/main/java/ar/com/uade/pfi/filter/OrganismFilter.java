package ar.com.uade.pfi.filter;

import java.util.Date;

public interface OrganismFilter {

	Long getIdOrganism();

	String getName();

	Double getPorcGC();

	Double getBestPearson();

	Date getLastUpdate();

}