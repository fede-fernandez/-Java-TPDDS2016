package ar.edu.dds.tpa.model;

import java.time.Duration;
import java.time.LocalDate;

public class BusquedaRealizada {
	private String textoBuscado;
	private int cantidadDeResultados;
	private LocalDate fechaDeBusqueda;
	private Duration tiempoDeRespuesta;

	public BusquedaRealizada(String textoBuscado, int cantidadDeResultados, Duration tiempoDeRespuesta) {
		this.textoBuscado = textoBuscado;
		this.cantidadDeResultados = cantidadDeResultados;
		this.fechaDeBusqueda = LocalDate.now();
		this.tiempoDeRespuesta = tiempoDeRespuesta;
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
	
	public double getTiempoDeRespuestaEnSegundos() {
		return tiempoDeRespuesta.toMillis() * 1000; //Un segundo = 1000 milisegundos
	}
}