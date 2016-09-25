package ar.edu.dds.tpa.reporte;

import java.util.List;
import java.time.LocalDate;
import java.util.HashMap;

import ar.edu.dds.tpa.model.HistorialDeBusqueda;

public class ReporteDeCantidadDeBusquedasPorFecha implements Reporte {

	private HashMap<String, Integer> reporte;

	public ReporteDeCantidadDeBusquedasPorFecha() {
		reporte = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> generarReporte(HistorialDeBusqueda historial) {
		List<LocalDate> fechasDeBusquedas = historial.fechasDeBusquedas();

		fechasDeBusquedas.forEach(
				unaFecha -> reporte.put(unaFecha.toString(), historial.cantidadDeBusquedasEnUnaFecha(unaFecha)));

		return reporte;
	}
}