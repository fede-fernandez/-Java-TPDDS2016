package ar.edu.dds.tpa.model;

import java.time.*;
import java.util.*;




public class Busqueda {
	
	private String fraseBuscada;
	private int cantidadDeResultados;
	private Duration tiempoDeRespuesta;	
	private LocalDateTime fechaDeBusqueda;
	private List<PuntoDeInteres> puntosDeInteresEncontrados;
	
	
	public Busqueda(String fraseBuscada, List<PuntoDeInteres> puntosDeInteresEncontrados, Duration tiempoDeRespuesta) {
		this.fraseBuscada = fraseBuscada;
		this.cantidadDeResultados = puntosDeInteresEncontrados.size();
		this.tiempoDeRespuesta = tiempoDeRespuesta;
		this.fechaDeBusqueda = LocalDateTime.now();
		this.puntosDeInteresEncontrados = puntosDeInteresEncontrados;
	}
	
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}


	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}


	public long getTiempoDeRespuesta() {
		return tiempoDeRespuesta.toMillis();
	}


	public LocalDateTime getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}
	
	public List<PuntoDeInteres> getPuntosDeInteresEncontrados(){
		return puntosDeInteresEncontrados;
	}
	

}
