package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

//TODO sacar los code smells
public class Banco extends PuntoDeInteres {
	private List<DiaYHorarioDeAtencion> diasYHorariosDeAtencion = new ArrayList<DiaYHorarioDeAtencion>();

	public Banco(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
		RangoDeHorario de10a15 = new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(15, 0));
		DiaYHorarioDeAtencion lunes10a15 = new DiaYHorarioDeAtencion(DayOfWeek.MONDAY);
		lunes10a15.agregarRangoDeHorario(de10a15);
		DiaYHorarioDeAtencion martes10a15 = new DiaYHorarioDeAtencion(DayOfWeek.TUESDAY);
		martes10a15.agregarRangoDeHorario(de10a15);
		DiaYHorarioDeAtencion miercoles10a15 = new DiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY);
		miercoles10a15.agregarRangoDeHorario(de10a15);
		DiaYHorarioDeAtencion jueves10a15 = new DiaYHorarioDeAtencion(DayOfWeek.THURSDAY);
		jueves10a15.agregarRangoDeHorario(de10a15);
		DiaYHorarioDeAtencion viernes10a15 = new DiaYHorarioDeAtencion(DayOfWeek.FRIDAY);
		viernes10a15.agregarRangoDeHorario(de10a15);
		this.agregarDiaYHorarioDeAtencion(lunes10a15);
		this.agregarDiaYHorarioDeAtencion(martes10a15);
		this.agregarDiaYHorarioDeAtencion(miercoles10a15);
		this.agregarDiaYHorarioDeAtencion(jueves10a15);
		this.agregarDiaYHorarioDeAtencion(viernes10a15);
	}
	
	public void agregarDiaYHorarioDeAtencion(DiaYHorarioDeAtencion unDiaYHorarioDeAtencion) {
		this.diasYHorariosDeAtencion.add(unDiaYHorarioDeAtencion);
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.diasYHorariosDeAtencion.stream().anyMatch(
				diaYHorario -> diaYHorario.estaDentroDelDiaYHorarioDeAtencion(unDiaYHorario));
	}
}
