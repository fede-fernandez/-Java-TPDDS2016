package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.Transient;


@Entity
public class HorarioDeAtencion {
	
	
	@Id
	@GeneratedValue
	private Long id_horarioDeAtencion;

//	@ElementCollection
//	@MapKeyColumn (name="rangoDeHorario")
//	@JoinTable(name="Agenda", joinColumns=@JoinColumn(name="id_rangoDeHorario"))
//	@Column(name="rangoDeHorario")
	@Transient 
	private Map<DayOfWeek, List<RangoDeHorario>> horarioDeAtencion;

	
	public HorarioDeAtencion() {
		horarioDeAtencion = new HashMap<DayOfWeek, List<RangoDeHorario>>();
	}

	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		RangoDeHorario rangoDeHorario = new RangoDeHorario(horarioDesde, horarioHasta);
		horarioDeAtencion.putIfAbsent(unDia, new ArrayList<RangoDeHorario>());
		horarioDeAtencion.get(unDia).add(rangoDeHorario);
	}

	public void agregarHorarioDeAtencion(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		dias.stream().forEach(unDia -> agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta));
	}

	public boolean seAtiendeEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.containsKey(unDiaYHorario.getDayOfWeek())
				&& horarioDeAtencion.get(unDiaYHorario.getDayOfWeek()).stream().anyMatch(
						rangoDeHorario -> rangoDeHorario.estaDentroDelRangoDeHorario(unDiaYHorario.toLocalTime()));
	}
}