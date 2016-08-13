package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HorarioDeAtencion {

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