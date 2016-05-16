package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Servicio {
	private String nombre;
	private List<DiaYHorarioDeAtencion> diasYHorariosDeAtencion;
	
	public Servicio(String nombre) {
		this.nombre = nombre;
		this.diasYHorariosDeAtencion = new ArrayList<DiaYHorarioDeAtencion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarDiaYHorarioDeAtencion(
			DiaYHorarioDeAtencion unDiaYHorarioDeAtencion) {
		this.diasYHorariosDeAtencion.add(unDiaYHorarioDeAtencion);
	}

	public boolean atiendeEn(LocalDateTime unDiaYHorario) {
		return this.diasYHorariosDeAtencion.stream().anyMatch(
				diaYHorario -> diaYHorario
						.estaDentroDelDiaYHorarioDeAtencion(unDiaYHorario));
	}
	
	public ArrayList<String> getEtiquetas(){
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.addAll(Arrays.asList(this.nombre.split(" ")));
		return etiquetas;
	}
}