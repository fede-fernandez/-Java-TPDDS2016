package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Servicio {
	private String nombre;
	private HorarioDeAtencion horarioSemanal;
	
	public Servicio(String nombre) {
		this.nombre = nombre;
		this.horarioSemanal = new HorarioDeAtencion();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarDiaYHorarioDeAtencion(DayOfWeek dia, RangoDeHorario rango) {
		this.horarioSemanal.agregarRangoDeHorario(dia, rango);
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.horarioSemanal.estaDentroDeRangosDeHorario(unDiaYHorario);
	}
	
	public boolean estasIncluidoEnElNombre(String unTexto){
		return nombre == unTexto;
	}
	
	public ArrayList<String> getEtiquetas(){
		ArrayList<String> etiquetas = new ArrayList<>();
		etiquetas.addAll(Arrays.asList(this.nombre.split(" ")));
		return etiquetas;
	}
}
