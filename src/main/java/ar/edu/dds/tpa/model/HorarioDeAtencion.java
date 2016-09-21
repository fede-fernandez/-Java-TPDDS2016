package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class HorarioDeAtencion {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(mappedBy="horarioDeAtencion")
	@MapKeyColumn(name = "dia")
	private Map<DayOfWeek, IntervalosDeHorario> horarioDeAtencion;

	public HorarioDeAtencion() {
		horarioDeAtencion = new HashMap<DayOfWeek, IntervalosDeHorario>();
	}

	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		RangoDeHorario rangoDeHorario = new RangoDeHorario(horarioDesde, horarioHasta);
		horarioDeAtencion.putIfAbsent(unDia, new IntervalosDeHorario());
		horarioDeAtencion.get(unDia).agregar(rangoDeHorario);
	}

	public void agregarHorarioDeAtencion(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		dias.stream().forEach(unDia -> agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta));
	}

	public boolean seAtiendeEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.containsKey(unDiaYHorario.getDayOfWeek())
				&& horarioDeAtencion.get(unDiaYHorario.getDayOfWeek()).obtenerRangosDeHorario().stream().anyMatch(
						rangoDeHorario -> rangoDeHorario.estaDentroDelRangoDeHorario(unDiaYHorario.toLocalTime()));
	}
}