package ar.edu.dds.tpa.persistencia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.morphia.converter.LocalDateConverter;
import ar.edu.dds.tpa.properties.Propiedades;

public class MorphiaDatastoreMongo {
	
	private Morphia morphia;
	private Datastore datastore;
	private Propiedades propiedades;
	private static MorphiaDatastoreMongo morphiaDatastore;
	private MongoClient mongoClient;
		
	private MorphiaDatastoreMongo(){
		propiedades = new Propiedades("resources/mongo.properties");
		morphia = new Morphia();
		
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().addMappedClass(Busqueda.class);
			
		mongoClient = new MongoClient(new MongoClientURI(propiedades.obtenerValorDe("URI")));
		datastore = morphia.createDatastore(mongoClient, propiedades.obtenerValorDe("nombreDeBD"));
	}
	
	public static MorphiaDatastoreMongo obtenerInstancia(){
		if (morphiaDatastore == null) {
			morphiaDatastore = new MorphiaDatastoreMongo();
		}
		return morphiaDatastore;
	}
	
	public Datastore getDatastore(){
		return datastore;
	}
}