package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DiaYHorarioDeAtencion {
	DayOfWeek dia;
	List<RangoDeHorario> rangosDeHorario = new ArrayList<RangoDeHorario>();

	public DiaYHorarioDeAtencion(DayOfWeek dia) {
		this.dia = dia;
	}

	public void agregarRangoDeHorario(RangoDeHorario rangoDeHorario) {
		this.rangosDeHorario.add(rangoDeHorario);
	}

	public boolean estaDentroDeRangosDeHorario(LocalTime unHorario) {
		return this.rangosDeHorario.stream()
				.anyMatch(rangoDeHorario -> rangoDeHorario.estaDentroDelRangoDeHorario(unHorario));
	}

	public boolean estaDentroDelDiaYHorarioDeAtencion(DayOfWeek unDia, LocalTime unHorario) {
		return unDia.equals(dia) && estaDentroDeRangosDeHorario(unHorario);
	}

	public boolean estaDentroDelDiaYHorarioDeAtencion(LocalDateTime unDiaYHorario) {
		return unDiaYHorario.getDayOfWeek().equals(dia) && estaDentroDeRangosDeHorario(unDiaYHorario.toLocalTime());
	}
}