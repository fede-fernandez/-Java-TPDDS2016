package ar.edu.dds.tpa.model;


import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.observer.BusquedaObserver;


public class Usuario {
	private String nombre;
	private Posicion coordenadas;
	private int comuna;
	private List<BusquedaObserver> observadoresDeBusqueda;
	
	public Usuario(){
		
	}	
	
	public Usuario(String nombre, Posicion coordenadas, int comuna) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.comuna = comuna;		
		observadoresDeBusqueda = new ArrayList<BusquedaObserver>();
	}

	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public int getComuna() {
		return comuna;
	}	
	
	public void notificarBusqueda(Busqueda unaBusquedaRealizada){
		observadoresDeBusqueda.forEach(unObservador -> unObservador.informar(unaBusquedaRealizada));
	}
	
	public void agregarObservadorDeBusqueda(BusquedaObserver unObservadorDeBusqueda) {
		observadoresDeBusqueda.add(unObservadorDeBusqueda);
	}
	
	public void quitarObservadorDeBusqueda(BusquedaObserver unObservadorDeBusqueda){
		observadoresDeBusqueda.remove(unObservadorDeBusqueda);
	}
	
}