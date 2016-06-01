package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.model.HorarioDeAtencion.Dia;

//TODO: Comportamiento repetido en horarios de atencion
public class Servicio {
	private String nombre;
	private HorarioDeAtencion horarioDeAtencion;
	
	public Servicio(String nombre) {
		this.nombre = nombre;
		horarioDeAtencion = new HorarioDeAtencion();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarHorarioDeAtencion(Dia unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta);
	}
	
	public void agregarHorarioDeAtencion(List<Dia> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(dias, horarioDesde, horarioHasta);
	}

	public boolean atiendeEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.seAtiendeEn(unDiaYHorario);
	}
	
	public boolean atiendeEn(Dia unDia, LocalTime unHorario) {
		return horarioDeAtencion.seAtiendeEn(unDia, unHorario);
	}
	
	public ArrayList<String> getEtiquetas(){
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.addAll(Arrays.asList(nombre.split(" ")));
		return etiquetas;
	}
}