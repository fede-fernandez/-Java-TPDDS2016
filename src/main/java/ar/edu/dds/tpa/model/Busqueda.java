package ar.edu.dds.tpa.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Busqueda {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private Usuario usuario;
	
	private String textoBuscado;
	
	private Integer cantidadDeResultados;
	
	private LocalDate fechaDeBusqueda;
	
	private Double tiempoDeRespuesta;

	public Busqueda(Usuario usuario, String textoBuscado, Integer cantidadDeResultados, LocalDate fechaDeBusqueda,
			Double tiempoDeRespuesta) {
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

	public Integer getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}

	public Double getTiempoDeRespuesta() {
		return tiempoDeRespuesta;
	}
}