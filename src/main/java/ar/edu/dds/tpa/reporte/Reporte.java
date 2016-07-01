package ar.edu.dds.tpa.reporte;

import java.util.HashMap;

import ar.edu.dds.tpa.model.HistorialDeBusqueda;

public interface Reporte {
	
	public HashMap<String, Integer> generarReporte(HistorialDeBusqueda historial);

}
