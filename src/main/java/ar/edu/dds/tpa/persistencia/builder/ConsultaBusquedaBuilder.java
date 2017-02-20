package ar.edu.dds.tpa.persistencia.builder;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaBusqueda;

public class ConsultaBusquedaBuilder {
	
	private List<ConsultaBusqueda> consultas;
	private Query<Busqueda> consulta;
	
	public Query<Busqueda> construirConsulta(Datastore datastore){
		consulta = datastore.createQuery(Busqueda.class);
		
		consultas.stream().forEach(unaConsulta -> consulta = unaConsulta.generarConsulta(consulta));
		
		return consulta;		
	}
	
	public void setConsultas(List<ConsultaBusqueda> consultas){
		this.consultas = consultas;
	}
	
	
}
