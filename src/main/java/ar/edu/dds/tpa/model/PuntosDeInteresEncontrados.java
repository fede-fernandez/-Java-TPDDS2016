package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class PuntosDeInteresEncontrados {
	
	@Id
	private ObjectId id;
	
	List<PuntoDeInteres> puntosDeInteresEncontrados;
	
	public PuntosDeInteresEncontrados(){
		puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
	}
	
	public PuntosDeInteresEncontrados(List<PuntoDeInteres> poisEncontrados){
		puntosDeInteresEncontrados = poisEncontrados;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public List<PuntoDeInteres> getPuntosDeInteresEncontrados() {
		return puntosDeInteresEncontrados;
	}

	public void setPuntosDeInteresEncontrados(List<PuntoDeInteres> puntosDeInteresEncontrados) {
		this.puntosDeInteresEncontrados = puntosDeInteresEncontrados;
	}
	
	public int getCantidadDeResultados(){
		return puntosDeInteresEncontrados.size();
	}

}
