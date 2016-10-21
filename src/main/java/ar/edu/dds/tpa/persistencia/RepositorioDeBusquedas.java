package ar.edu.dds.tpa.persistencia;

import java.time.LocalDate;
import java.util.List;

import org.mongodb.morphia.Datastore;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.HistorialDeBusqueda;

public class RepositorioDeBusquedas {

	private Datastore datastore;

	public RepositorioDeBusquedas(Datastore datastore) {
		this.datastore = datastore;
	}
	
	public void guardar(Busqueda busqueda){
		datastore.save(busqueda);
	}
	
	public HistorialDeBusqueda traerTodasLasBusquedas(){
		List<Busqueda> todasLasBusquedas = datastore.find(Busqueda.class).asList();
		
		return new HistorialDeBusqueda(todasLasBusquedas);		
	}
	
	public HistorialDeBusqueda encontrarTodasLasBusquedasPorTextoBuscado(String unTexto){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("textoBuscado").equal(unTexto).asList();
		
		return new HistorialDeBusqueda(resultado);
	}
	
	public HistorialDeBusqueda encontrarLasBusquedasEntreDosFechas(LocalDate fechaDeInicio, LocalDate fechaDeFin){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("fechaDeBusqueda").greaterThanOrEq(fechaDeInicio)
				.field("fechaDeBusqueda").lessThanOrEq(fechaDeFin).asList();
		
		return new HistorialDeBusqueda(resultado);				
	}
	
	public HistorialDeBusqueda encontrarLasBusquedasEntreDosFechasYPorTextoBuscado(String textoBuscado, LocalDate fechaDeInicio, LocalDate fechaDeFin){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("fechaDeBusqueda").greaterThanOrEq(fechaDeInicio)
				.field("fechaDeBusqueda").lessThanOrEq(fechaDeFin)
				.field("textoBuscado").equal(textoBuscado).asList();
		
		return new HistorialDeBusqueda(resultado);
	}
	
	public HistorialDeBusqueda encontrarLasBusquedasQueSeRealizaronEnUnaFecha(LocalDate unaFecha){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("fechaDeBusqueda").equal(unaFecha).asList();
		
		return new HistorialDeBusqueda(resultado);
	}
	
	public void eliminarBusqueda(Busqueda unaBusqueda){
		datastore.delete(unaBusqueda);
	}

}
