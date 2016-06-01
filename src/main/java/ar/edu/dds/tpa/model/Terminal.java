package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
	
	
	String nombre;
	List<Busqueda> busquedasRealizadas = new ArrayList<Busqueda>();
	
	
	public void agregarBusquedaRealizada(Busqueda busqueda){
		busquedasRealizadas.add(busqueda);
	}

}
