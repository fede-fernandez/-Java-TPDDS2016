package ar.edu.dds.tpa.persistencia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.HistorialDeBusqueda;
import ar.edu.dds.tpa.properties.Propiedades;

public class MorphiaDatastore {
	
	private Morphia morphia;
	private Datastore datastore;
	private Propiedades propiedades;
	private static MorphiaDatastore morphiaDatastore;
	private MongoClient mongoClient;
	
	
	private MorphiaDatastore(){
		propiedades = new Propiedades("resources/mongo.properties");
		morphia = new Morphia();
		morphia.map(Busqueda.class,HistorialDeBusqueda.class);
		mongoClient = new MongoClient(new MongoClientURI(System.getProperty("MONGO_URI", "mongodb://localhost:27017")));
		datastore = morphia.createDatastore(mongoClient, propiedades.obtenerValorDe("nombreDeBD"));
	}
	
	public static MorphiaDatastore obtenerInstancia(){
		if (morphiaDatastore == null) {
			morphiaDatastore = new MorphiaDatastore();
		}
		return morphiaDatastore;
	}
	
	public Datastore getDatastore(){
		return datastore;
	}

}
