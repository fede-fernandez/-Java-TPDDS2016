package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="Servicio")
public class Servicio {
	
	@Id
	@GeneratedValue
	@Column(name="id_servicio")
	private Long id;
	  
	private String nombre;
	
	@Transient 
	private HorarioDeAtencion horarioDeAtencion;
	
	public Servicio() {
		super();
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