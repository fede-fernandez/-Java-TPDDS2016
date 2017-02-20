package ar.edu.dds.tpa.persistencia.consulta;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class ConsultaPorTerminal implements ConsultaBusqueda{
	
	private String nombreDeTerminal;

	public ConsultaPorTerminal(String nombreDeTerminal) {
		this.nombreDeTerminal = nombreDeTerminal;
	}

	@Override
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta) {
		return consulta.field("terminal.nombre").equal(nombreDeTerminal);
	}

}
