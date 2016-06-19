package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class HistorialDeBusquedas {
	
	private List<BusquedaRealizada> historial;	
	
	
	public HistorialDeBusquedas(){
		historial = new ArrayList<BusquedaRealizada>();
	}
	
	
	public void registrarBusqueda(BusquedaRealizada busqueda){
		historial.add(busqueda);
	}
	
	
	public List<LocalDate> fechasDeBusquedas(){
		return historial.stream()
				.map(unaBusqueda -> unaBusqueda.getFechaDeBusqueda())
				.distinct()
				.collect(Collectors.toList());
	}
	
	
	public List<String> textosBuscados(){
		return historial.stream()
				.map(unaBusqueda -> unaBusqueda.getTextoBuscado())
				.collect(Collectors.toList());
	}
	
	
	public int cantidadDeResultadosTotales(){
		return historial.stream()
				.mapToInt(unaBusqueda -> unaBusqueda.getCantidadDeResultados())
				.sum();
	}
	
	
	public int cantidadDeBusquedasEnUnaFecha(LocalDate unaFecha){
		return historial.parallelStream()
				.filter(unaBusqueda -> unaBusqueda.getFechaDeBusqueda().equals(unaFecha))
				.mapToInt(unaBusquedaFiltrada -> unaBusquedaFiltrada.getCantidadDeResultados())
				.sum();
	}

}
