package ar.edu.dds.tpa.historial;

import java.time.LocalDate;
import java.util.List;

import org.mongodb.morphia.Datastore;

import ar.edu.dds.tpa.model.Busqueda;

public class HistorialDeBusquedaEnMongo implements HistorialDeBusqueda{

	private Datastore datastore;

	public HistorialDeBusquedaEnMongo(Datastore datastore) {
		this.datastore = datastore;
	}

	public void registrarBusqueda(Busqueda busqueda){
		datastore.save(busqueda);
	}
	
	public List<Busqueda> traerTodasLasBusquedas(){
		List<Busqueda> todasLasBusquedas = datastore.find(Busqueda.class).asList();
		
		return todasLasBusquedas;				
	}
	
	public List<Busqueda> encontrarTodasLasBusquedasPorTextoBuscado(String unTexto){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("textoBuscado").equal(unTexto).asList();
		
		return resultado;
	}
	
	public List<Busqueda> encontrarLasBusquedasEntreDosFechas(LocalDate fechaDeInicio, LocalDate fechaDeFin){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("fechaDeBusqueda").greaterThanOrEq(fechaDeInicio)
				.field("fechaDeBusqueda").lessThanOrEq(fechaDeFin).asList();
		
		return resultado;
	}
	
	public List<Busqueda> encontrarLasBusquedasEntreDosFechasYPorTextoBuscado(String textoBuscado, LocalDate fechaDeInicio, LocalDate fechaDeFin){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("fechaDeBusqueda").greaterThanOrEq(fechaDeInicio)
				.field("fechaDeBusqueda").lessThanOrEq(fechaDeFin)
				.field("textoBuscado").equal(textoBuscado).asList();
		
		return resultado;	
	}
	
	public List<Busqueda> encontrarLasBusquedasQueSeRealizaronEnUnaFecha(LocalDate unaFecha){
		List<Busqueda> resultado = datastore.createQuery(Busqueda.class)
				.field("fechaDeBusqueda").equal(unaFecha).asList();
		
		return resultado;
	}
	
	public void eliminarBusqueda(Busqueda unaBusqueda){
		datastore.delete(unaBusqueda);
	}
	

}
