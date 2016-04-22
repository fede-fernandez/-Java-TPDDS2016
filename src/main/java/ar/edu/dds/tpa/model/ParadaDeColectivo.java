package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends PuntoDeInteres {

	public ParadaDeColectivo(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
	}
	
	@Override
	public boolean estaCercaDe(Point unaPosicion) {
		return this.getCoordenadas().distance(unaPosicion) <= 0.1;
	}
	
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return true;
	}
}
