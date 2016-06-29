package ar.edu.dds.tpa.model;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public class Usuario {
	String nombre;
	Posicion coordenadas;
	
	public Usuario(String nombre, Posicion coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
	}
}