package ar.com.uade.pfi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.uade.pfi.manager.ProcessingManagerV2;
import ar.com.uade.pfi.model.service.ResponseServiceModel;


@Path("/process")
public class ProcessRestService {	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseServiceModel process() {
    	ResponseServiceModel rsm = new ResponseServiceModel();
//        ProcessingManager pm = ProcessingManager.getInstance();
    	ProcessingManagerV2 pm = ProcessingManagerV2.getInstance();
        if (pm.getStatus().isLock()) {
        	rsm.setRes(1);
        	return rsm;
        }
        
        try {
			pm.process();
			rsm.setRes(0);
		} catch (Exception e) {
			rsm.setRes(1);
		}
        
        return rsm;
    }
}
