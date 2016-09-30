package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

@Entity
public class Banco extends PuntoDeInteresConServicios {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "horarioDeAtencion_id")
	private HorarioDeAtencion horarioDeAtencionBancario;

	public Banco() {
		super();
	}

	public Banco(String nombre, Posicion coordenadas) {
		super(nombre, coordenadas);
		List<DayOfWeek> deLunesAViernes = new ArrayList<DayOfWeek>();
		deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		horarioDeAtencionBancario = new HorarioDeAtencion();
		horarioDeAtencionBancario.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(10, 0), LocalTime.of(15, 0));
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencionBancario.seAtiendeEn(unDiaYHorario);
	}
}