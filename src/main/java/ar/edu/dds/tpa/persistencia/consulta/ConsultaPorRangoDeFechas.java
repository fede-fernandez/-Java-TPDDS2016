package ar.edu.dds.tpa.persistencia.consulta;

import java.time.LocalDate;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public class ConsultaPorRangoDeFechas implements ConsultaBusqueda{

	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	
	public ConsultaPorRangoDeFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	@Override
	public Query<Busqueda> generarConsulta(Query<Busqueda> consulta) {
		return consulta.field("fechaDeBusqueda").greaterThanOrEq(fechaInicial)
				.field("fechaDeBusqueda").lessThanOrEq(fechaFinal);
	}
	
	
	
	
}
