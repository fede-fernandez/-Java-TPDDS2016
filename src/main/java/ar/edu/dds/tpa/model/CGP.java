package ar.edu.dds.tpa.model;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Poligono;

@Entity
public class CGP extends PuntoDeInteresConServicios {

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cgp_id")
	private Set<Poligono> zonasDeCobertura;

	public CGP() {
		super();
	}

	public CGP(String nombre, Posicion coordenadas) {
		super(nombre, coordenadas);
		zonasDeCobertura = new HashSet<Poligono>();
	}

	public void agregarZonaDeCobertura(Poligono unaZona) {
		zonasDeCobertura.add(unaZona);
	}

	public Set<Poligono> getZonasDeCobertura() {
		return zonasDeCobertura;
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return super.estaCercaDe(unaPosicion)
				|| zonasDeCobertura.stream().anyMatch(unaZona -> unaZona.incluyeA(unaPosicion));
	}
}