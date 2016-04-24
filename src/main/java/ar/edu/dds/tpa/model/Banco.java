package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

//TODO sacar los code smells
public class Banco extends PuntoDeInteres {
	private HorarioDeAtencion horarioSemanal;

	public Banco(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
		horarioSemanal = new HorarioDeAtencion();
	}
	
	public void agregarDiaYHorarioDeAtencion(DayOfWeek dia, RangoDeHorario rango) {
		this.horarioSemanal.agregarRangoDeHorario(dia, rango);
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.horarioSemanal.estaDentroDeRangosDeHorario(unDiaYHorario);
	}

//	@Override
//	public boolean condicionDeBusqueda(String unTexto) {
//		return this.estaEtiquetadoPor(unTexto);
//	}
}
