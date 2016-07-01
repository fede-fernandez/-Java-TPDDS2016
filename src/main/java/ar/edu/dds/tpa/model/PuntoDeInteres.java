package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public abstract class PuntoDeInteres {
	private String nombre;
	private Posicion coordenadas;
	private List<String> palabrasClave;

	public PuntoDeInteres(String nombre, Posicion coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		palabrasClave = new ArrayList<String>();
		agregarPalabraClave(nombre);
	}
	
	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}
	
	public List<String> getPalabrasClave() {
		return palabrasClave;
	}
	
	public void agregarPalabraClave(String unaPalabraClave) {
		palabrasClave.add(unaPalabraClave.toLowerCase());
	}
	
	public boolean estaCercaDe(Posicion unaPosicion) {
		return coordenadas.distanciaA(unaPosicion) <= 0.5;
	}

	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);
	
	public boolean contienePalabraClave(String unaPalabra) {
		return palabrasClave.stream().anyMatch(unaPalabraClave -> unaPalabraClave.contains(unaPalabra.toLowerCase()));
	}
	
	public void borrarPalabrasClaves(){
		palabrasClave = new ArrayList<String>();
		agregarPalabraClave(nombre);

	}
}