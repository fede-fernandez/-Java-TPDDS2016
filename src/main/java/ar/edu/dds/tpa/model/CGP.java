package ar.edu.dds.tpa.model;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Poligono;

@Entity
public class CGP extends PuntoDeInteresConServicios {
	
	@OneToMany
	@JoinTable(name = "ZonasDeCobertura", inverseJoinColumns = @JoinColumn(name = "zona_id"))
	private List<Poligono> zonasDeCobertura;

	public CGP() {
		super();
	}

	public CGP(String nombre, Posicion coordenadas) {
		super(nombre, coordenadas);
		zonasDeCobertura = new ArrayList<Poligono>();
	}

	public void agregarZonaDeCobertura(Poligono unaZona) {
		zonasDeCobertura.add(unaZona);
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return super.estaCercaDe(unaPosicion)
				|| zonasDeCobertura.stream().anyMatch(unaZona -> unaZona.incluyeA(unaPosicion));
	}
}