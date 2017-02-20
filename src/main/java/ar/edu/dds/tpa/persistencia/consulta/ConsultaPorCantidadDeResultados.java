package ar.edu.dds.tpa.persistencia.consulta;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class ConsultaPorCantidadDeResultados implements ConsultaBusqueda{

	private int cantidadDeResultados;

	public ConsultaPorCantidadDeResultados(Integer cantidadDeResultados) {
		this.cantidadDeResultados = cantidadDeResultados;
	}

	@Override
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta) {
		return consulta.field("cantidadDeResultados").equal(cantidadDeResultados);
	}
	
	
}
