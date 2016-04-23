package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class LocalComercial extends PuntoDeInteres {
	private Rubro rubro;
	private HorarioDeAtencion horarioSemanal;
	
	public LocalComercial(String nombre, Point coordenadas, Rubro rubro) {
		super(nombre, coordenadas);
		this.rubro = rubro;
	}

	public Rubro getRubro() {
		return rubro;
	}
	
	public void agregarDiaYHorarioDeAtencion(DayOfWeek dia, RangoDeHorario rango) {
		this.horarioSemanal.agregarRangoDeHorario(dia, rango);
	}
	
	@Override
	public boolean estaCercaDe(Point unaPosicion){
		return this.getCoordenadas().distance(unaPosicion) <= this.rubro.radioDeCercania() * 0.1;
	}
	
	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.horarioSemanal.estaDentroDeRangosDeHorario(unDiaYHorario);
	}

	@Override
	public boolean condicionDeBusqueda(String unTexto) {
		return rubro.getNombreRubro() == unTexto
				|| this.estaEtiquetadoPor(unTexto);
	}
	

}
