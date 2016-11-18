package ar.edu.dds.tpa.datastoreTest;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;

import com.github.fakemongo.Fongo;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.morphia.converter.LocalDateConverter;

public class MorphiaDatastoreTest {
	
	private static MorphiaDatastoreTest morphiaDatastore;
	private Morphia morphia;
	private Datastore datastore;
	private Fongo fongo;
	private Mapper mapper;
	
	private MorphiaDatastoreTest(){
		morphia = new Morphia();
		fongo = new Fongo("ServerTest");
		
		mapper = new Mapper();
		mapper.addMappedClass(Busqueda.class);
		mapper.getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().addMappedClass(Busqueda.class);
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		
		datastore = morphia.createDatastore(fongo.getMongo(), "baseDeTesting");
		
	}
	
	public static MorphiaDatastoreTest obtenerInstancia(){
		if (morphiaDatastore == null) {
			morphiaDatastore = new MorphiaDatastoreTest();
		}
		return morphiaDatastore;
	}
	
	public Datastore getDatastore(){
		return datastore;
	}

}
