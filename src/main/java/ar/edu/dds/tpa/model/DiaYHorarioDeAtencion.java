package ar.edu.dds.tpa.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DiaYHorarioDeAtencion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private DayOfWeek dia;

	@ElementCollection
	@CollectionTable(name="RangosDeHorariosPorDia")
	private Set<RangoDeHorario> rangosDeHorario;

	public DiaYHorarioDeAtencion() {
		rangosDeHorario = new HashSet<RangoDeHorario>();
	}

	public DiaYHorarioDeAtencion(DayOfWeek dia) {
		this.dia = dia;
		rangosDeHorario = new HashSet<RangoDeHorario>();
	}

	public void agregarHorario(LocalTime horarioDesde, LocalTime horarioHasta) {
		RangoDeHorario rangoDeHorario = new RangoDeHorario(horarioDesde, horarioHasta);
		rangosDeHorario.add(rangoDeHorario);
	}

	public DayOfWeek getDia() {
		return dia;
	}
	
	public Set<RangoDeHorario> getRangosDeHorario() {
		return rangosDeHorario;
	}

	public boolean seAtiendeEn(LocalDateTime unDiaYHorario) {
		return rangosDeHorario.stream()
				.anyMatch(rangoDeHorario -> rangoDeHorario.estaDentroDelRangoDeHorario(unDiaYHorario.toLocalTime()));
	}
}