package ar.edu.dds.tpa.reporte;

import java.util.HashMap;
import java.util.List;

import ar.edu.dds.tpa.model.HistorialDeBusqueda;

public class ReporteDeCantidadDeResultadosTotalesPorUsuario implements Reporte {

	private HashMap<String, Integer> reporte;

	public ReporteDeCantidadDeResultadosTotalesPorUsuario(){
		reporte = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> generarReporte(HistorialDeBusqueda historial) {
		List<String> nombresDeUsuarios = historial.nombresDeUsuarios();
		
		nombresDeUsuarios.forEach(unNombre -> reporte.put(unNombre, historial.cantidadDeResultadosTotalesDeUnUsuario(unNombre)));
		
		return reporte;
	}

}
