package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.observer.BusquedaObserver;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordenadas")
	private Posicion coordenadas;

	@ManyToOne(cascade = CascadeType.ALL)
	private Comuna comuna;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<BusquedaObserver> observadoresDeBusqueda;

	public Usuario() {

	}

	public Usuario(String nombre, Posicion coordenadas, Comuna comuna) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.comuna = comuna;
		observadoresDeBusqueda = new ArrayList<BusquedaObserver>();
	}

	public Integer getId() {
		return id;
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
}