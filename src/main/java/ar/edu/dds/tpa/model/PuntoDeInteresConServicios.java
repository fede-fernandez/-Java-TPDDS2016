package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

@Entity
public abstract class PuntoDeInteresConServicios extends PuntoDeInteres {
	
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="pi")
	private List<Servicio> servicios;

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