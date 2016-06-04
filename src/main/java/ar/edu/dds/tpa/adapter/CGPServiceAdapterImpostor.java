package ar.edu.dds.tpa.adapter;

import java.util.List;

import ar.edu.dds.tpa.adapter.model.CentroDTO;
import ar.edu.dds.tpa.model.CGP;

public class CGPServiceAdapterImpostor implements CGPServiceAdapter {
	private boolean seLlamoAlCGPService = false;

	public boolean seLlamoAlCGPService() {
		return seLlamoAlCGPService;
	}

	@Override
	public List<CGP> obtenerCGPsDeListaCentrosDTO(List<CentroDTO> centrosDTO) {
		seLlamoAlCGPService = true;
		return null;
	}
}
