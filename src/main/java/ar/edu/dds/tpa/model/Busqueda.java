package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity
public class Busqueda {
	
	@Id
	private ObjectId id;

	private Terminal terminal;

	private String textoBuscado;
	
	private List<PuntoDeInteres> puntosDeInteresEncontrados;

	private LocalDate fechaDeBusqueda;

	private Double tiempoDeRespuesta;

	public Busqueda() {
		puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
	}

	public Busqueda(Terminal terminal, String textoBuscado, List<PuntoDeInteres> puntosDeInteresEncontrados,
			LocalDate fechaDeBusqueda, Double tiempoDeRespuesta) {
		this.terminal = terminal;
		this.textoBuscado = textoBuscado;
		this.puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>(puntosDeInteresEncontrados);
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.tiempoDeRespuesta = tiempoDeRespuesta;
	}


	public Terminal getTerminal() {
		return terminal;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public List<PuntoDeInteres> getPuntosDeInteresEncontrados() {
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

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}