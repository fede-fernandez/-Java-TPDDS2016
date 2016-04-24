package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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

	protected ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.addAll(Arrays.asList(this.nombre.split(" ")));
		return etiquetas;
	}

	public boolean estaCercaDe(Point unaPosicion){
		return this.coordenadas.distance(unaPosicion) <= 0.5;				
	}
	
	public boolean estaEtiquetadoPor(String unTexto){
		return getEtiquetas().stream().anyMatch(etiqueta -> etiqueta.equalsIgnoreCase(unTexto));
	}
	
	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
	
	public boolean condicionDeBusqueda(String unTexto){
		return getEtiquetas().stream().anyMatch(etiqueta -> unTexto.equalsIgnoreCase(etiqueta));
	};

	
}
