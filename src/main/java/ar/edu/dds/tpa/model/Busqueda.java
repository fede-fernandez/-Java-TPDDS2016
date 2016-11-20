package ar.edu.dds.tpa.model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;


@Entity
public class Busqueda {
	
	@Id
	private ObjectId id;

	private Terminal terminal;

	private String textoBuscado;
	@Reference(lazy=true,idOnly=true)
	private PuntosDeInteresEncontrados puntosDeInteresEncontrados;

	private LocalDate fechaDeBusqueda;

	private Double tiempoDeRespuesta;
	
	private int cantidadDeResultados;

	public Busqueda() {

	}

	public Busqueda(Terminal terminal, String textoBuscado, PuntosDeInteresEncontrados poisEncontrados,
			LocalDate fechaDeBusqueda, Double tiempoDeRespuesta) {
		this.terminal = terminal;
		this.textoBuscado = textoBuscado;
		this.puntosDeInteresEncontrados = poisEncontrados;
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.tiempoDeRespuesta = tiempoDeRespuesta;
		cantidadDeResultados = poisEncontrados.getCantidadDeResultados();
	}


	public Terminal getTerminal() {
		return terminal;
	}

	public String getTextoBuscado() {
		return textoBuscado;
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

	public PuntosDeInteresEncontrados getPuntosDeInteresEncontrados() {
		return puntosDeInteresEncontrados;
	}

	public void setPuntosDeInteresEncontrados(PuntosDeInteresEncontrados puntosDeInteresEncontrados) {
		this.puntosDeInteresEncontrados = puntosDeInteresEncontrados;
	}
	
	public String getUrl(){
		return "historico/" + getId().toString(); 
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public void setCantidadDeResultados(int cantidadDeResultados) {
		this.cantidadDeResultados = cantidadDeResultados;
	}
}