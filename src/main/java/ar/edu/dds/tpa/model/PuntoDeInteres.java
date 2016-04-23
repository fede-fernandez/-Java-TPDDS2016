package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.uqbar.geodds.Point;

public abstract class PuntoDeInteres {
	private String nombre;
	private Point coordenadas;

	public PuntoDeInteres(String nombre, Point coordenadas) {
		super();
		this.nombre = nombre;
		this.coordenadas = coordenadas;
	}

	public String getNombre() {
		return nombre;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	private ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.add(this.nombre);
		return etiquetas;
	}

	public boolean estaCercaDe(Point unaPosicion){
		return this.coordenadas.distance(unaPosicion) <= 0.5;				
	}
	
	public boolean estaEtiquetadoPor(String unTexto){
		return getEtiquetas().stream().anyMatch(etiqueta -> etiqueta.equalsIgnoreCase(unTexto));
	}
	
	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
	
	public abstract boolean condicionDeBusqueda(String unTexto);

	
}
