package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public abstract class PuntoDeInteres {
	private String nombre;
	private Point coordenadas;

	public PuntoDeInteres(String nombre, Point coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Point getCoordenadas() {
		return coordenadas;
	}
	
	public boolean estaCercaDe(Point unaPosicion){
		return this.coordenadas.distance(unaPosicion) <= 0.5;				
	}
	
	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
}
