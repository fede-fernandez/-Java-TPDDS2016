package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

//TODO sacar los code smells
public class Banco extends PuntoDeInteresConServicios {
	//private HorarioDeAtencion horarioSemanal;

	public Banco(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
		//horarioSemanal = new HorarioDeAtencion();
	}
}
