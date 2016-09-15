package ar.edu.dds.tpa.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javafx.util.converter.LocalTimeStringConverter;




@Entity
public class RangoDeHorario {
	
	@Id
	@GeneratedValue
	@Column(name="id_rangoDeHorario")
	private Long id_rangoDeHorario;	
	

	private LocalTime horarioInicio;

	private LocalTime horarioFin;
	
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