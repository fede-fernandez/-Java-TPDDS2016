package ar.edu.dds.tpa.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.persistencia.MorphiaDatastore;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaBusqueda;

public class ConsultaBusquedaBuilder implements MorphiaDatastore{
	
	private List<ConsultaBusqueda> consultas = new ArrayList<ConsultaBusqueda>();
	private Query<Busqueda> consulta;
	
	public Query<Busqueda> construirConsulta(){
		consulta = datastore.createQuery(Busqueda.class);
		
		consultas.stream().forEach(unaConsulta -> consulta = unaConsulta.generarConsulta(consulta));
		
		return consulta;		
	}
	
	public void setConsultas(List<ConsultaBusqueda> consultas){
		this.consultas = consultas;
	}
	
	
}
