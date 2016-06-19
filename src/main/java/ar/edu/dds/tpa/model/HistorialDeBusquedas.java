package ar.edu.dds.tpa.model;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class HistorialDeBusquedas {
	
	private List<BusquedaRealizada> historial;	
	
	
	public void registrarBusqueda(BusquedaRealizada busqueda){
		historial.add(busqueda);
	}
	
	public List<LocalDate> fechasDeBusquedas(){
		return historial.stream().map(unaBusqueda -> unaBusqueda.getFechaDeBusqueda()).distinct().collect(Collectors.toList());
	}

}
