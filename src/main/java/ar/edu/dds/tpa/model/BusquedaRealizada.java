package ar.edu.dds.tpa.model;

import java.time.LocalDate;

public class BusquedaRealizada {
	private String textoBuscado;
	private int cantidadDeResultados;
	private LocalDate fechaDeBusqueda;

	public BusquedaRealizada(String textoBuscado, int cantidadDeResultados, LocalDate fechaDeBusqueda) {
		this.textoBuscado = textoBuscado;
		this.cantidadDeResultados = cantidadDeResultados;
		this.fechaDeBusqueda = fechaDeBusqueda;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}
	
	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}
}