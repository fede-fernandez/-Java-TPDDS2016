package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Servicio {
	private String nombre;
	private HorarioDeAtencion horarioSemanal;
	
	public Servicio(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarDiaYHorarioDeAtencion(DayOfWeek dia, RangoDeHorario rango) {
		this.horarioSemanal.agregarRangoDeHorario(dia, rango);
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.horarioSemanal.estaDentroDeRangosDeHorario(unDiaYHorario);
	}
	
	public boolean estasIncluidoEnElNombre(String unTexto){
		return nombre == unTexto;
	}
}
