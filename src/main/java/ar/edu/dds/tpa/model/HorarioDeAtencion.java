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

	// Constructor de HorarioDeAtencion
	// No requiere parametros, solamente instancia la coleccion.
	public HorarioDeAtencion() {
		horarioDeAtencion = new HashMap<DayOfWeek, List<RangoDeHorario>>();
	}

	// agregarHorarioDeAtencion(dia, horarioDesde, horarioHasta)
	// Agrega a la coleccion el dia (si no existe)
	// Si existe se le agrega el rango de horario (horarioInicio, horarioFin)
	// synchronized permite que los objetos se instancien una unica vez
	// (thread-safe).
	public synchronized void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		RangoDeHorario rangoDeHorario = new RangoDeHorario(horarioDesde, horarioHasta);
		horarioDeAtencion.putIfAbsent(unDia, new ArrayList<RangoDeHorario>());
		horarioDeAtencion.get(unDia).add(rangoDeHorario);
	}
	
	// agregarHorarioDeAtencion(dias, horarioDesde, horarioHasta)
	// Agrega a la coleccion una lista de dias que tengan el mismo horario
	// de atencion en comun.
	public synchronized void agregarHorarioDeAtencion(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		dias.stream().forEach(unDia -> agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta));
	}

	// seAtienteEn (diaYhorario)
	// Retorna verdadero o falso dependiendo si el dia y horario
	// ingresados se encuentran dentro de los horarios de atencion.
	public boolean seAtiendeEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.containsKey(unDiaYHorario.getDayOfWeek()) && horarioDeAtencion.get(unDiaYHorario.getDayOfWeek()).stream()
				.anyMatch(rangoDeHorario -> rangoDeHorario.estaDentroDelRangoDeHorario(unDiaYHorario.toLocalTime()));
	}
	
	// seAtienteEn (dia, horario)
	// Retorna verdadero o falso dependiendo si el dia y horario
	// ingresados se encuentran dentro de los horarios de atencion.
	public boolean seAtiendeEn(DayOfWeek unDia, LocalTime unHorario) {
		return horarioDeAtencion.containsKey(unDia) && horarioDeAtencion.get(unDia).stream()
				.anyMatch(rangoDeHorario -> rangoDeHorario.estaDentroDelRangoDeHorario(unHorario));
	}
}