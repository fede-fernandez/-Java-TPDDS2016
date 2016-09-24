package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

@Entity
public class Servicio {

	@Id
	@GeneratedValue
	private Integer id;

	private String nombre;

	@OneToOne(cascade = CascadeType.ALL)
	private HorarioDeAtencion horarioDeAtencion;

	public Servicio() {
		horarioDeAtencion = new HorarioDeAtencion();
	}

	public Servicio(String nombre) {
		this.nombre = nombre;
		horarioDeAtencion = new HorarioDeAtencion();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta);
	}

	public void agregarHorarioDeAtencion(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(dias, horarioDesde, horarioHasta);
	}

	public boolean atiendeEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.seAtiendeEn(unDiaYHorario);
	}
}