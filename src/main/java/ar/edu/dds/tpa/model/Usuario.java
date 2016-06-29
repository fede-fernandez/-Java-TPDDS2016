package ar.edu.dds.tpa.model;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public class Usuario {
	Posicion coordenadas;
	public Usuario(Posicion coordenadas) {
		this.coordenadas = coordenadas;
	}
}