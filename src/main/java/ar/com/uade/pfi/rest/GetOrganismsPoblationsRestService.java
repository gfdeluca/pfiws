package ar.com.uade.pfi.rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.uade.pfi.dao.AppConfigurationDao;
import ar.com.uade.pfi.dao.OrganismDao;
import ar.com.uade.pfi.dao.dbo.entities.AppConfigurationEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;
import ar.com.uade.pfi.manager.ProcessingManager;
import ar.com.uade.pfi.model.service.ResponseServiceModel;
import ar.com.uade.pfi.utils.ArrayUtils;


@Path("/getOrgPob")
public class GetOrganismsPoblationsRestService {

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
        
        Map<String, Object> response = new HashMap<String, Object>();
        AppConfigurationEntity appConfGammas = null;
        List<OrganismEntity> oes = null;
		try {
			appConfGammas = AppConfigurationDao.get("process.gammas");
			oes = OrganismDao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Double> gammas = ArrayUtils.arrayOFStringToDouble(appConfGammas.getValor().split(","));
        
        
        //Inicializo el arreglo
        Map<String, TreeMap<Double, Double>> gammaPearsonByOrganism = new HashMap<String, TreeMap<Double,Double>>();
        for (Iterator<OrganismEntity> itOe = oes.iterator(); itOe.hasNext();) {
        	OrganismEntity oe = itOe.next();
        	
        	TreeMap<Double, Double> gammaPearson = new TreeMap<Double, Double>();
        	for (Iterator<Double> itGamma = gammas.iterator(); itGamma.hasNext();) {
        		Double gamma = itGamma.next();
        		gammaPearson.put(gamma, 0.0);
        	}
        	
        	gammaPearsonByOrganism.put(oe.getName(), gammaPearson);
        }
        
        //Pongo los valores de gamma y pearson en el array, ademas busco el mejor gamma a traves del R de Pearson
//        List<OrganismPoblationFilter> opes = OrganismPoblationDao.getAll();
        
//        for (Iterator<OrganismPoblationFilter> itOpes = opes.iterator(); itOpes.hasNext();) {
//			OrganismPoblationEntity ope = itOpes.next();
			
//			gammaPearsonByOrganism.get(ope.getIdOrganim().getName()).put(ope.getGamma(), ope.getPearsonCoefficient());
//		}
        
        response.put("OrganismsPoblations", gammaPearsonByOrganism);
        response.put("gammas", gammas);
        
        rsm.setResponse(response);
        rsm.setRes(0);
        return rsm;
    }
}
