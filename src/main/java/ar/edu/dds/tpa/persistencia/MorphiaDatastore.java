package ar.edu.dds.tpa.persistencia;

import org.mongodb.morphia.Datastore;

public interface MorphiaDatastore {
	Datastore datastore = MorphiaDatastoreMock.obtenerInstancia().getDatastore();
}
