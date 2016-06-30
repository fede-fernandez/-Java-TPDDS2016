package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.uqbar.geodds.Point;

public abstract class PuntoDeInteres {
	private String nombre;
	private Point coordenadas;
	private LocalDateTime fechaBaja;

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

	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
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

	
	
	@Override
	public boolean equals(Object otro) {		
		//Nos referimos al mismo punto de interes si ambos objetos son exactamente el mismo, o si tienen el mismo nombre y la ubicacion es aproximadamente la misma con un leve margen de error (2mm)
		return otro != null &&
			   (otro == this ||
				   (Math.abs(this.coordenadas.distance(((PuntoDeInteres)otro).getCoordenadas())) <= 0.0000002 
				   && this.getNombre().equals(((PuntoDeInteres)otro).getNombre())));
	}

	public boolean estaActivo() {
		return fechaBaja == null || fechaBaja.isAfter(LocalDateTime.now());
	}
}
