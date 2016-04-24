package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public abstract class PuntoDeInteres {
	private String nombre;
	private Posicion coordenadas;

	public PuntoDeInteres(String nombre, Posicion coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
	}

	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public boolean estaCercaDe(Posicion unaPosicion) {
		return this.coordenadas.distanciaA(unaPosicion) <= 0.5;
	}

	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
}