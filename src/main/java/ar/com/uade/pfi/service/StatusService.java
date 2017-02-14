package ar.com.uade.pfi.service;

import ar.com.uade.pfi.manager.ProcessingManager;
import ar.com.uade.pfi.model.experimet.StatusServiceModel;

public class StatusService {

    public StatusServiceModel getStatus() {
    	
    	return ProcessingManager.getInstance().getStatus();
    }
}
