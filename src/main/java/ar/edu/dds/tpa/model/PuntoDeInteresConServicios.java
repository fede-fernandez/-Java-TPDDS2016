package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoDeInteresConServicios extends PuntoDeInteres {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ServicioDePuntoDeInteres", inverseJoinColumns = @JoinColumn(name = "servicio_id"))
	private List<Servicio> servicios;
	
	public PuntoDeInteresConServicios() {
		servicios = new ArrayList<Servicio>();
	}

	public PuntoDeInteresConServicios(String nombre, Posicion coordenadas) {
		super(nombre, coordenadas);
		servicios = new ArrayList<Servicio>();
	}

	public void agregarServicio(Servicio unServicio) {
		servicios.add(unServicio);
		super.agregarPalabraClave(unServicio.getNombre());
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return servicios.stream().anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario, String nombreDelServicio) {
		return servicios.stream().filter(servicio -> servicio.getNombre().equalsIgnoreCase(nombreDelServicio))
				.anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}
}