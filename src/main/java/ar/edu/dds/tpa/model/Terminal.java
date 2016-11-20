package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.observer.BusquedaObserver;

@Entity
public class Terminal extends Usuario{

	private String nombre;

	@Embedded
	private Posicion coordenadas;

	@ManyToOne(cascade = CascadeType.ALL)
	private Comuna comuna;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "terminal_id")
	private List<BusquedaObserver> observadoresDeBusqueda;

	public Terminal() {

	}

	public Terminal(String nombre, Posicion coordenadas, Comuna comuna) {
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

	public Comuna getComuna() {
		return comuna;
	}

	public List<BusquedaObserver> getObservadoresDeBusqueda() {
		return observadoresDeBusqueda;
	}

	public void notificarBusqueda(Busqueda unaBusquedaRealizada) {
		observadoresDeBusqueda.forEach(unObservador -> unObservador.informar(unaBusquedaRealizada));
	}

	public void agregarObservadorDeBusqueda(BusquedaObserver unObservadorDeBusqueda) {
		observadoresDeBusqueda.add(unObservadorDeBusqueda);
	}

	public void quitarObservadorDeBusqueda(BusquedaObserver unObservadorDeBusqueda) {
		observadoresDeBusqueda.remove(unObservadorDeBusqueda);
	}
	@Override
	public String getUrl(){
		return "busquedaVisualizacionPOIs/buscarPOIs.hbs";
	}
}