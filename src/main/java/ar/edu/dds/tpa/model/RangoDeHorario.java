package ar.edu.dds.tpa.model;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class RangoDeHorario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalTime horarioInicio;

	private LocalTime horarioFin;
	
	public RangoDeHorario() {
		
	}

	public RangoDeHorario(LocalTime horarioInicio, LocalTime horarioFin) {
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}

	public boolean estaDespuesDelHorarioInicio(LocalTime unHorario) {
		return unHorario.isAfter(this.horarioInicio);
	}

	public boolean estaAntesDelHorarioFin(LocalTime unHorario) {
		return unHorario.isBefore(this.horarioFin);
	}

	public boolean estaDentroDelRangoDeHorario(LocalTime unHorario) {
		return estaDespuesDelHorarioInicio(unHorario) && estaAntesDelHorarioFin(unHorario);
	}
}