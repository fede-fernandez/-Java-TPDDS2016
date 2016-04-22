package ar.edu.dds.tpa.model;

import java.time.LocalTime;

public class RangoDeHorario {
	private LocalTime horarioInicio;
	private LocalTime horarioFin;
	
	public RangoDeHorario(LocalTime horarioInicio, LocalTime horarioFin) {
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}
	
	public boolean estaDespuesDelHorarioInicio(LocalTime unHorario) {
		return horarioInicio.isAfter(unHorario);
	}
	
	public boolean estaAntesDelHorarioFin(LocalTime unHorario) {
		return horarioFin.isBefore(unHorario);
	}
	
	public boolean estaDentroDelRangoDeHorario(LocalTime unHorario) {
		return estaDespuesDelHorarioInicio(unHorario) && estaAntesDelHorarioFin(unHorario);
	}
}
