package ar.com.uade.pfi.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.uade.pfi.manager.ProcessingManager;
import ar.com.uade.pfi.model.experimet.StatusServiceModel;
import ar.com.uade.pfi.model.service.ResponseServiceModel;


@Path("/status")
public class StatusRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel getStatus() {
    	ResponseServiceModel rsm = new ResponseServiceModel();
        
    	StatusServiceModel ssm = ProcessingManager.getInstance().getStatus();
    	rsm.setRes(ssm.isLock()?1:0);
    	Map<String, Integer> response = new HashMap<String, Integer>();
    	response.put("progress", ssm.getProgress());
    	response.put("total", ssm.getTotal());
    	rsm.setResponse(response);
    	
    	return rsm;
    }
}
