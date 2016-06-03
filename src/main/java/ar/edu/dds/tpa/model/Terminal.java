package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.*;

public class Terminal{
	
	
	String nombre;
	Buscador buscador;
	List<Busqueda> busquedasRealizadas;
	List<ObserverBusquedas> observadores;
	
	public Terminal(String nombre, Buscador buscador) {
		this.nombre = nombre;
		this.buscador = buscador;
		this.busquedasRealizadas = new ArrayList<Busqueda>();
		this.observadores = new ArrayList<ObserverBusquedas>();
	}
	
	public void buscarPuntosDeInteres(String textoDeBusqueda){
		Instant comienzo = Instant.now();
		
		List<PuntoDeInteres> puntosDeInteresEncontrados = buscador.buscarPorTextoLibre(textoDeBusqueda);
		
		Instant finalizacion = Instant.now();
		
		this.agregarBusquedaRealizada(new Busqueda(textoDeBusqueda,puntosDeInteresEncontrados, Duration.between(comienzo, finalizacion)));		
		
	}
	
	
	public void agregarBusquedaRealizada(Busqueda busqueda){		
		observadores.forEach(observador -> observador.informar(busqueda));
		busquedasRealizadas.add(busqueda);
	}
	
	public void registrarObserverDeBusquedas(ObserverBusquedas observer){
		observadores.add(observer);
	}
	
	
	public long cantidadDeBusquedasPorFecha(LocalDate fecha){
		return busquedasRealizadas.stream().filter(unaBusqueda -> fecha.equals(unaBusqueda.getFechaDeBusqueda())).count();
	}
	
	
	
	

}
