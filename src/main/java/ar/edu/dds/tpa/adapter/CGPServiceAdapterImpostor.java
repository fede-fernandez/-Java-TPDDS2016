package ar.edu.dds.tpa.adapter;

import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.adapter.model.CentroDTO;
import ar.edu.dds.tpa.model.CGP;

public class CGPServiceAdapterImpostor implements CGPServiceAdapter {
	private boolean seLlamoAlCGPServiceAdapter = false;

	@Override
	public List<CGP> obtenerCGPsDeListaCentrosDTO(List<CentroDTO> centrosDTO) {
		List<CGP> cgpsDelServicioImpostor = new ArrayList<CGP>();
		seLlamoAlCGPServiceAdapter = true;
		return cgpsDelServicioImpostor;
	}
	
	public boolean seLlamoAlCGPServiceAdapter() {
		return seLlamoAlCGPServiceAdapter;
	}
}
