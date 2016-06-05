package ar.edu.dds.tpa.service;

import java.util.List;

import ar.edu.dds.tpa.adapter.model.CentroDTO;

public class CGPServiceImpostor implements CGPService {
	private boolean seLlamoAlCGPService = false;
	
	@Override
	public List<CentroDTO> getCGPsByCalleOBarrio(String calleOBarrio) {
		seLlamoAlCGPService = true;
		return null;
	}
	
	public boolean seLlamoAlCGPService() {
		return seLlamoAlCGPService;
	}
}