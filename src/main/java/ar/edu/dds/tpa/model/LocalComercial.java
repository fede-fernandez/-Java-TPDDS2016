package ar.edu.dds.tpa.model;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public class LocalComercial extends PuntoDeInteres {
	

	private Rubro rubro;
	private HorarioDeAtencion horarioDeAtencion;

	public LocalComercial(String nombre, Posicion coordenadas, Rubro rubro) {
		super(nombre, coordenadas);
		this.rubro = rubro;
		horarioDeAtencion = new HorarioDeAtencion();
	}

	public Rubro getRubro() {
		return rubro;
	}
		
	
	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta);
	}
	
	public void agregarHorarioDeAtencion(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(dias, horarioDesde, horarioHasta);
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return getCoordenadas().distanciaA(unaPosicion) <= rubro.radioDeCercania() * 0.1;
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.seAtiendeEn(unDiaYHorario);
	}
	
	@Override
	public ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = super.getEtiquetas();
		etiquetas.addAll(Arrays.asList(rubro.nombre().split(" ")));
		return etiquetas;
	}
}