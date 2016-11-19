package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

@Entity
public class ParadaDeColectivo extends PuntoDeInteres {
	
	public ParadaDeColectivo() {
		super();
	}

	public ParadaDeColectivo(String nombre, Posicion coordenadas,String direccion) {
		super(nombre, coordenadas,direccion);
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