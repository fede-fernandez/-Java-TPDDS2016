package ar.edu.dds.tpa.model;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import ar.edu.dds.tpa.geolocalizacion.Poligono;


@Entity
public class CGP extends PuntoDeInteresConServicios {
	
	@Transient 
	private List<Poligono> zonasDeCobertura;

	public CGP(String nombre, Posicion coordenadas) {
		super(nombre, coordenadas);
		zonasDeCobertura = new ArrayList<Poligono>();
	}

	public void agregarZonaDeCobertura(Poligono unaZona) {
		zonasDeCobertura.add(unaZona);
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return super.estaCercaDe(unaPosicion) || zonasDeCobertura.stream().anyMatch(unaZona -> unaZona.incluyeA(unaPosicion));
	}
}