package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import ar.edu.dds.tpa.geolocalizacion.Posicion;


public abstract class PuntoDeInteres {
	private String nombre;

	private Posicion coordenadas;
	
	protected String etiquetaPalabraClave;

	public PuntoDeInteres(String nombre, Posicion coordenadas,String etiquetaPalabraClave) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.etiquetaPalabraClave = etiquetaPalabraClave;
	}
	

	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}


	public boolean estaCercaDe(Posicion unaPosicion) {
		return coordenadas.distanciaA(unaPosicion) <= 0.5;
	}

	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
	
	protected ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.add(etiquetaPalabraClave);
		etiquetas.addAll(Arrays.asList(nombre.split(" ")));
		return etiquetas;
	}
	
	public boolean condicionDeBusqueda(String unTexto) {
		return getEtiquetas().stream().anyMatch(etiqueta -> unTexto.equalsIgnoreCase(etiqueta));
	}
	

	
	
}