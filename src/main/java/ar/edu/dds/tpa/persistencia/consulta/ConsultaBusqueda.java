package ar.edu.dds.tpa.persistencia.consulta;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public interface ConsultaBusqueda {
	
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta);

}
