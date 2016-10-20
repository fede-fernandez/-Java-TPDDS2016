package ar.edu.dds.tpa.persistencia;

import org.mongodb.morphia.Datastore;

import ar.edu.dds.tpa.model.HistorialDeBusqueda;

public class RepositorioDeBusquedas {

	private Datastore datastore;

	public RepositorioDeBusquedas(Datastore datastore) {
		this.datastore = datastore;
	}
	
	public void guardar(HistorialDeBusqueda historialDeBusqueda){
		datastore.save(historialDeBusqueda);
	}
	
}
