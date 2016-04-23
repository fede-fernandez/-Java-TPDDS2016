package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

//TODO sacar los code smells
public class Banco extends PuntoDeInteres {
	private HorarioDeAtencion horarioSemanal;

	public Banco(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
		RangoDeHorario de10a15 = new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(15, 0));
		HorarioDeAtencion horario = new HorarioDeAtencion();
		horario.agregarRangoDeHorario(DayOfWeek.MONDAY, de10a15);
		horario.agregarRangoDeHorario(DayOfWeek.TUESDAY, de10a15);
		horario.agregarRangoDeHorario(DayOfWeek.WEDNESDAY, de10a15);
		horario.agregarRangoDeHorario(DayOfWeek.THURSDAY, de10a15);
		horario.agregarRangoDeHorario(DayOfWeek.FRIDAY, de10a15);
	}
	
	public void agregarDiaYHorarioDeAtencion(DayOfWeek dia, RangoDeHorario rango) {
		this.horarioSemanal.agregarRangoDeHorario(dia, rango);
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.horarioSemanal.estaDentroDeRangosDeHorario(unDiaYHorario);
	}

	@Override
	public boolean condicionDeBusqueda(String unTexto) {
		return this.estaEtiquetadoPor(unTexto);
	}
}
