package ar.edu.dds.tpa.persistencia.repository.historial;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class HistorialDeBusquedaEnMongo implements HistorialDeBusqueda{

	private Datastore datastore;

	public HistorialDeBusquedaEnMongo(Datastore datastore) {
		this.datastore = datastore;
	}

	public void registrarBusqueda(Busqueda busqueda){
		datastore.save(busqueda.getPuntosDeInteresEncontrados());
		datastore.save(busqueda);
	}
		
	public void eliminarBusqueda(Busqueda unaBusqueda){
		datastore.delete(unaBusqueda);
	}
	
	public Busqueda encontrarBusquedaPorId(String id){
		ObjectId objectId = new ObjectId(id);
		
		return datastore.find(Busqueda.class).field("id").equal(objectId).get();
	}
	
	public Busqueda encontrarBusquedaPorId(ObjectId id){
		return datastore.find(Busqueda.class).field("id").equal(id).get();
	}
	
	public List<Busqueda> traerTodasLasBusquedas(){
		List<Busqueda> todasLasBusquedas = datastore.find(Busqueda.class).asList();
		
		return todasLasBusquedas;				
	}
	
	public List<Busqueda> ejecutarConsulta(Query<Busqueda> consulta){
		return consulta.asList();
	}	

}
