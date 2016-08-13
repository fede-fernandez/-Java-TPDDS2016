package ar.edu.dds.tpa.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.adapter.model.CentroDTO;
import ar.edu.dds.tpa.adapter.model.RangoServicioDTO;
import ar.edu.dds.tpa.adapter.model.ServicioDTO;

public class CGPServiceImpostor implements CGPService {
	private boolean seLlamoAlCGPService = false;
	
	@Override
	public List<CentroDTO> getCGPsByCalleOBarrio(String calleOBarrio) {
		seLlamoAlCGPService = true;
		List<CentroDTO> cgpsEncontrados = new ArrayList<CentroDTO>();
		if(calleOBarrio.contains("Flores")) {
			List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
			List<RangoServicioDTO> rangoServicioDTO = new ArrayList<RangoServicioDTO>();
			rangoServicioDTO.add(new RangoServicioDTO(1, 12, 20, 18, 15));
			serviciosDTO.add(new ServicioDTO("rentas", rangoServicioDTO));
			cgpsEncontrados.add(new CentroDTO(7, "Flores, Parque Chacabuco", "Juan Arriaga", "Av. Rivadavia 7202", "4637-2355", serviciosDTO));
		}
		return cgpsEncontrados;
	}
	
	public boolean seLlamoAlCGPService() {
		return seLlamoAlCGPService;
	}
}