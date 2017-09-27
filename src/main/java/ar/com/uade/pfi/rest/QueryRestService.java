package ar.com.uade.pfi.rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.uade.pfi.dao.CalculatedCodonPoblationDao;
import ar.com.uade.pfi.dao.ExperimentalCodonPoblationDao;
import ar.com.uade.pfi.dao.OrganismDao;
import ar.com.uade.pfi.dao.OrganismPoblationDao;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.manager.ProcessingManager;
import ar.com.uade.pfi.model.service.CalculatedCodonPoblationView;
import ar.com.uade.pfi.model.service.CoordinateModel;
import ar.com.uade.pfi.model.service.ResponseServiceModel;


@Path("/query")
public class QueryRestService {
	private static final Logger logger = LogManager.getLogger(QueryRestService.class);

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
        List<OrganismEntity> oes = null;
		try {
			oes = OrganismDao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        rsm.setResponse(oes);
        return rsm;
    }
	
	@GET
    @Path("{idOrganism}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel getOrganismPoblations(@PathParam("idOrganism") long idOrganism, @QueryParam("precision") int precision, @QueryParam("minGamma") double minGamma, @QueryParam("maxGamma") double maxGamma) {    
		
		logger.info("Invocando al servicio getOrganismPoblations con ID: {}, con una precision de {}", idOrganism, precision);
		
    	ResponseServiceModel rsm = new ResponseServiceModel();
        ProcessingManager pm = ProcessingManager.getInstance();
        if (pm.getStatus().isLock()) {
        	logger.info("No es posible realizar la solicitud por que los datos se estan recalculando");
        	rsm.setRes(1);
        	rsm.setResponse("Imposible acceder a los datos, los mismo se estan re calculando en este momento.");
        	return rsm;
        }
        
        rsm.setRes(0);
        double porc = precision/100.0;
        List<CoordinateModel> coord = null;
		try {
			coord = OrganismPoblationDao.getAllByOrganism(idOrganism, porc, minGamma, maxGamma);
		} catch (SQLException e) {
			logger.error("Ocurrio un error al recuperar los datos de los organismos de la base de datos", e);
		}
       
        rsm.setResponse(coord);
        logger.info("Finalizando el servicio getOrganismPoblations");
        return rsm;
    }
	    
    @GET
    @Path("{idOrganism}/{gamma}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel getCodonsPoblation(@PathParam("idOrganism") long idOrganism, @PathParam("gamma") double gamma) {
    	
    	logger.info("Invocando al servicio getCodonsPoblation con idOrganism: {} y Gamma: {}", idOrganism, gamma);
    	
    	ResponseServiceModel rsm = new ResponseServiceModel();
        ProcessingManager pm = ProcessingManager.getInstance();
        if (pm.getStatus().isLock()) {
        	rsm.setRes(1);
        	rsm.setResponse("Imposible acceder a los datos, los mismo se estan re calculando en este momento.");
        	return rsm;
        }
        
        rsm.setRes(0);
        List<CoordinateModel> ccpes = null;
        List<CoordinateModel> ecpes = null;
        List<CalculatedCodonPoblationView> ccpv = null;
        double pearsonCoefficient = 0;
		try {
			ccpes = CalculatedCodonPoblationDao.getAllByOrganismPoblationByCoordinates(idOrganism, gamma);
			ecpes = ExperimentalCodonPoblationDao.getAllByOrganismByCoordinates(idOrganism);
			ccpv = CalculatedCodonPoblationDao.getAllByOrganismPoblation(idOrganism, gamma);
			pearsonCoefficient = OrganismPoblationDao.getOrganismPearsonCoefficientByGamma(idOrganism, gamma);
		} catch (SQLException e) {
			logger.error("Ocurrio un error al recuperar los datos de las poblaciones de la base de datos", e);
		}
        
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("pearsonCoefficient", pearsonCoefficient);
        response.put("CalculatedCodonPoblation", ccpes);
        response.put("ExperimentalCodonPoblation", ecpes);
        response.put("CodonsInformation", ccpv);
        
        rsm.setResponse(response);
        logger.info("Finalizando el servicio getCodonsPoblation");
        return rsm;
    }
}
