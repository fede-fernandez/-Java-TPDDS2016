package ar.edu.dds.tpa.persistencia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;

import com.github.fakemongo.Fongo;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.morphia.converter.LocalDateConverter;

public class MorphiaDatastoreMock {
	
	private static MorphiaDatastoreMock morphiaDatastore;
	private Morphia morphia;
	private Datastore datastore;
	private Fongo fongo;
	private Mapper mapper;
	
	private MorphiaDatastoreMock(){
		morphia = new Morphia();
		fongo = new Fongo("ServerTest");
		
		mapper = new Mapper();
		mapper.addMappedClass(Busqueda.class);
		mapper.getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().addMappedClass(Busqueda.class);
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		
		datastore = morphia.createDatastore(fongo.getMongo(), "baseDeTesting");
		
	}
	
	public static MorphiaDatastoreMock obtenerInstancia(){
		if (morphiaDatastore == null) {
			morphiaDatastore = new MorphiaDatastoreMock();
		}
		return morphiaDatastore;
	}
	
	public Datastore getDatastore(){
		return datastore;
	}

}
