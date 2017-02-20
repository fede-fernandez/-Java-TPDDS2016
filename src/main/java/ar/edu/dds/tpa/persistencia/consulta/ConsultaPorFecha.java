package ar.edu.dds.tpa.persistencia.consulta;

import java.time.LocalDate;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class ConsultaPorFecha implements ConsultaBusqueda{
	
	private LocalDate fechaDeBusqueda;

	public ConsultaPorFecha(LocalDate fechaDeBusqueda) {
		this.fechaDeBusqueda = fechaDeBusqueda;
	}

	@Override
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta) {
		return consulta.field("fechaDeBusqueda").equal(fechaDeBusqueda);
	}
	
	

}
