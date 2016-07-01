package ar.edu.dds.tpa.model;

import java.time.LocalDate;

public class Busqueda {
	private Usuario usuario;
	private String textoBuscado;
	private int cantidadDeResultados;
	private LocalDate fechaDeBusqueda;
	private double tiempoDeRespuesta;

	public Busqueda(Usuario usuario, String textoBuscado, int cantidadDeResultados, LocalDate fechaDeBusqueda,
			double tiempoDeRespuesta) {
		this.usuario = usuario;
		this.textoBuscado = textoBuscado;
		this.cantidadDeResultados = cantidadDeResultados;
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.tiempoDeRespuesta = tiempoDeRespuesta;
	}

	public Usuario getUsuario() {
		return usuario;
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

	public double getTiempoDeRespuesta() {
		return tiempoDeRespuesta;
	}
}