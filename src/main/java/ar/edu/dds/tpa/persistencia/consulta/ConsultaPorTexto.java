package ar.edu.dds.tpa.persistencia.consulta;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class ConsultaPorTexto implements ConsultaBusqueda{

	private String textoBuscado;

	public ConsultaPorTexto(String textoBuscado) {
		this.textoBuscado = textoBuscado;
	}

	@Override
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta) {
		return consulta.field("textoBuscado").containsIgnoreCase(textoBuscado);
	}
	
	
	
}
