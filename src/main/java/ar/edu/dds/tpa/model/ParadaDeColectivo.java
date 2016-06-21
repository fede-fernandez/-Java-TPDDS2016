package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public class ParadaDeColectivo extends PuntoDeInteres {
	public ParadaDeColectivo(String nombre, Posicion coordenadas) {
		super(nombre, coordenadas);
	}
	
	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return getCoordenadas().distanciaA(unaPosicion) <= 0.1;
	}
	
	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return true;
	}
}