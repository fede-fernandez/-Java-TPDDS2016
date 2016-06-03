package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public class Terminal {
	private String nombre;
	private Posicion coordenadas;
	private Mapa mapa;
	private List<BusquedaRealizada> busquedasRealizadas;
	
	public Terminal(String nombre, Posicion coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		busquedasRealizadas = new ArrayList<BusquedaRealizada>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public List<PuntoDeInteres> buscarPorTextoLibre(String unaFrase) {
		busquedasRealizadas.add(new BusquedaRealizada(unaFrase, LocalDate.now()));
		return mapa.buscarPorTextoLibre(unaFrase);
	}
	
	public int cantidadDeBusquedasPorFecha(LocalDate unaFecha) {
		return (int) busquedasRealizadas.stream().filter(unaBusqueda -> unaBusqueda.getFechaDeBusqueda().equals(unaFecha)).count();
	}
	
	public int cantidadDeBusquedasPorTexto(String unTexto) {
		return (int) busquedasRealizadas.stream().filter(unaBusqueda -> unaBusqueda.getTextoBuscado().equals(unTexto)).count();
	}
}