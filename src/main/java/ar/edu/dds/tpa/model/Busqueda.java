package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.*;

@Entity
public class Busqueda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	private String textoBuscado;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "PuntoDeInteresEncontrado", inverseJoinColumns = @JoinColumn(name = "puntoDeInteres_id"))
	private Set<PuntoDeInteres> puntosDeInteresEncontrados;

	private LocalDate fechaDeBusqueda;

	private Double tiempoDeRespuesta;

	public Busqueda() {
		puntosDeInteresEncontrados = new HashSet<PuntoDeInteres>();
	}

	public Busqueda(Usuario usuario, String textoBuscado, List<PuntoDeInteres> puntosDeInteresEncontrados,
			LocalDate fechaDeBusqueda, Double tiempoDeRespuesta) {
		this.usuario = usuario;
		this.textoBuscado = textoBuscado;
		this.puntosDeInteresEncontrados = new HashSet<PuntoDeInteres>(puntosDeInteresEncontrados);
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.tiempoDeRespuesta = tiempoDeRespuesta;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public Set<PuntoDeInteres> getPuntosDeInteresEncontrados() {
		return puntosDeInteresEncontrados;
	}

	public int getCantidadDeResultados() {
		return puntosDeInteresEncontrados.size();
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}

	public Double getTiempoDeRespuesta() {
		return tiempoDeRespuesta;
	}
}