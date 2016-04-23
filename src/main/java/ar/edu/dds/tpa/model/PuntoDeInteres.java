package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public abstract class PuntoDeInteres {
	private String nombre;
	private Point coordenadas;
	private String etiquetaPalabraClave;

	public PuntoDeInteres(String nombre, Point coordenadas, String etiquetaPalabraClave) {
		super();
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.etiquetaPalabraClave = etiquetaPalabraClave;
	}

	public String getNombre() {
		return nombre;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	public String getEtiquetaPalabraClave() {
		return etiquetaPalabraClave;
	}

	public boolean estaCercaDe(Point unaPosicion){
		return this.coordenadas.distance(unaPosicion) <= 0.5;				
	}
	
	public boolean estaEtiquetadoPor(String unTexto){
		return etiquetaPalabraClave == unTexto;
	}
	
	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
	
	public abstract boolean condicionDeBusqueda(String unTexto);

	
}
