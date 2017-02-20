package ar.edu.dds.tpa.reporte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusqueda;

public class Reporte {
	
	public static List<ItemDeResultadosPorFecha> generarReporteDeResultadosPorFecha(LocalDate fechaDeInicio, LocalDate fechaDeFin, HistorialDeBusqueda historialDeBusqueda){
		List<ItemDeResultadosPorFecha> reporteDeResultadosPorFecha = new ArrayList<ItemDeResultadosPorFecha>();
		
		//List<Busqueda> busquedas = historialDeBusqueda.encontrarLasBusquedasEntreDosFechas(fechaDeInicio, fechaDeFin);	
		
		return reporteDeResultadosPorFecha;
	}

}
