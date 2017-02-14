package ar.com.uade.pfi.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.uade.pfi.dao.CalculatedCodonPoblationDao;
import ar.com.uade.pfi.dao.ExperimentalCodonPoblationDao;
import ar.com.uade.pfi.dao.OrganismPoblationDao;
import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismPoblationEntity;
import ar.com.uade.pfi.filter.CalculatedCodonPoblationFilter;
import ar.com.uade.pfi.filter.ExperimentalCodonPoblationFilter;
import ar.com.uade.pfi.filter.OrganismPoblationFilter;
import ar.com.uade.pfi.manager.ProcessingManager;
import ar.com.uade.pfi.model.service.ResponseServiceModel;


@Path("/query")
public class QueryRestService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel getOrganisms() {    	
    	ResponseServiceModel rsm = new ResponseServiceModel();
        ProcessingManager pm = ProcessingManager.getInstance();
        if (pm.getStatus().isLock()) {
        	rsm.setRes(1);
        	rsm.setResponse("Imposible acceder a los datos, los mismo se estan re calculando en este momento.");
        	return rsm;
        }
        rsm.setRes(0);       
        List<OrganismEntity> oes = DBManager.getInstance().getAll(OrganismEntity.class);

        rsm.setResponse(oes);
        return rsm;
    }
	
    @GET
    @Path("{idOrganism}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel getOrganismPoblations(@PathParam("idOrganism") Long idOrganism) {    	
    	ResponseServiceModel rsm = new ResponseServiceModel();
        ProcessingManager pm = ProcessingManager.getInstance();
        if (pm.getStatus().isLock()) {
        	rsm.setRes(1);
        	rsm.setResponse("Imposible acceder a los datos, los mismo se estan re calculando en este momento.");
        	return rsm;
        }
        rsm.setRes(0);       
        OrganismEntity oe = DBManager.getInstance().get(OrganismEntity.class, idOrganism);
        List<OrganismPoblationFilter> ope = OrganismPoblationDao.getAllByOrganism(oe);
       
        rsm.setResponse(ope);
        return rsm;
    }
    
    @GET
    @Path("{idOrganism}/{idOrganismPoblation}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel getCodonsPoblation(@PathParam("idOrganism") Long idOrganism, @PathParam("idOrganismPoblation") Long idOrganismPoblation) {
    	ResponseServiceModel rsm = new ResponseServiceModel();
        ProcessingManager pm = ProcessingManager.getInstance();
        if (pm.getStatus().isLock()) {
        	rsm.setRes(1);
        	rsm.setResponse("Imposible acceder a los datos, los mismo se estan re calculando en este momento.");
        	return rsm;
        }
        
        rsm.setRes(0);
        OrganismEntity oe = DBManager.getInstance().get(OrganismEntity.class, idOrganism);
        OrganismPoblationEntity ope = DBManager.getInstance().get(OrganismPoblationEntity.class, idOrganismPoblation);
        List<CalculatedCodonPoblationFilter> ccpes = CalculatedCodonPoblationDao.getAllByOrganismPoblation(ope);
        List<ExperimentalCodonPoblationFilter> ecpes = ExperimentalCodonPoblationDao.getAllByOrganismFilter(oe);
        
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("CalculatedCodonPoblation", ccpes);
        response.put("ExperimentalCodonPoblation", ecpes);
        
        rsm.setResponse(response);
        return rsm;
    }
}
