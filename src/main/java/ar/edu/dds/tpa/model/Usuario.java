package ar.edu.dds.tpa.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.observer.BusquedaObserver;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id_usuario;
	
	private String nombre;
	@OneToOne
	private Posicion coordenadas;
	private int comuna;
	
	@Transient
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