package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

public class HistorialDeBusqueda {
	List<Busqueda> busquedasRealizadas;
	
	public HistorialDeBusqueda() {
		busquedasRealizadas = new ArrayList<Busqueda>();
	}
	
	public void agregarBusqueda(Busqueda unaBusqueda) {
		busquedasRealizadas.add(unaBusqueda);
	}
}