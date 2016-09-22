package ar.edu.dds.tpa.reporte;

import java.util.HashMap;

import ar.edu.dds.tpa.model.HistorialDeBusqueda;

public class ReporteDeResultadosPorBusqueda implements Reporte {

	private HashMap<String, Integer> reporte;

	public ReporteDeResultadosPorBusqueda() {
		reporte = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> generarReporte(HistorialDeBusqueda historial) {
		return reporte;
	}
}