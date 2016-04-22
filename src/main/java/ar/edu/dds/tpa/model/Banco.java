package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class Banco extends PuntoDeInteres {

	public Banco(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		// TODO Para los Bancos, el mismo comportamiento que para los CGP pero
		// considerando el horario de atención bancario (Lunes a Viernes de 10hs
		// a 15hs)
		return true;
	}
}
