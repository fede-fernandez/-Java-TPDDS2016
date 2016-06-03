package ar.edu.dds.tpa.model;

import java.time.LocalDate;

public class BusquedaRealizada {
	private String textoBuscado;
	private LocalDate fechaDeBusqueda;
	
	public BusquedaRealizada(String textoBuscado, LocalDate fechaDeBusqueda) {
		this.textoBuscado = textoBuscado;
		this.fechaDeBusqueda = fechaDeBusqueda;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}
}