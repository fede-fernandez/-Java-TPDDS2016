package ar.edu.dds.tpa.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public class LocalComercial extends PuntoDeInteres {
	

	private Rubro rubro;
	private List<DiaYHorarioDeAtencion> diasYHorariosDeAtencion;

	public LocalComercial(String nombre, Posicion coordenadas, Rubro rubro) {
		super(nombre, coordenadas);
		this.rubro = rubro;
		this.diasYHorariosDeAtencion = new ArrayList<DiaYHorarioDeAtencion>();
	}

	public Rubro getRubro() {
		return rubro;
	}
		
	
	public void agregarDiaYHorarioDeAtencion(DiaYHorarioDeAtencion unDiaYHorarioDeAtencion) {
		this.diasYHorariosDeAtencion.add(unDiaYHorarioDeAtencion);
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return this.getCoordenadas().distanciaA(unaPosicion) <= this.rubro.radioDeCercania() * 0.1;
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.diasYHorariosDeAtencion.stream()
				.anyMatch(diaYHorario -> diaYHorario.estaDentroDelDiaYHorarioDeAtencion(unDiaYHorario));
	}
	
	@Override
	public ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = super.getEtiquetas();
		etiquetas.addAll(Arrays.asList(this.rubro.nombre().split(" ")));
		return etiquetas;
	}
}