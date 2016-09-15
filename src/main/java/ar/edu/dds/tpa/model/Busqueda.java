package ar.edu.dds.tpa.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

@Entity
public class Busqueda {
	
	@Id
	@GeneratedValue
	private Long id_busqueda;
	
	@OneToOne
	private Usuario usuario;
	private String textoBuscado;
	private int cantidadDeResultados;
	@Convert(converter=LocalDateConverter.class)
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