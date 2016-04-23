package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class HorarioDeAtencion {
	private Hashtable<DayOfWeek, ArrayList<RangoDeHorario>> horarioSemanal = new Hashtable<DayOfWeek, ArrayList<RangoDeHorario>>();

	public void agregarRangoDeHorario(DayOfWeek dia, RangoDeHorario rangoDeHorario) {
		if (!horarioSemanal.containsKey(dia)){ // Si todavia no estaba el dia entre los horarios lo agrego
			horarioSemanal.put(dia, new ArrayList<RangoDeHorario>());
		}
		horarioSemanal.get(dia).add(rangoDeHorario);
	}

	public boolean estaDentroDeRangosDeHorario(LocalDateTime diaYHora) {
		return horarioSemanal.get(diaYHora.getDayOfWeek())
				.stream()
				.anyMatch(rango -> rango.estaDentroDelRangoDeHorario(diaYHora.toLocalTime()));
	}
}
