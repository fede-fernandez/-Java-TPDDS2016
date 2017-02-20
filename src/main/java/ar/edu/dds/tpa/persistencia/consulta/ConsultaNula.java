package ar.edu.dds.tpa.persistencia.consulta;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class ConsultaNula implements ConsultaBusqueda{

	@Override
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta) {
		return consulta;
	}

}
