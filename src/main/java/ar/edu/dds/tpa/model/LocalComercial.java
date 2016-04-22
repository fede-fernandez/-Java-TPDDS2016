package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class LocalComercial extends PuntoDeInteres {
	private Rubro rubro;
	private List<DiaYHorarioDeAtencion> diasYHhorariosDeAtencion = new ArrayList<DiaYHorarioDeAtencion>();
	
	public LocalComercial(String nombre, Point coordenadas, Rubro rubro) {
		super(nombre, coordenadas);
		this.rubro = rubro;
	}

	public Rubro getRubro() {
		return rubro;
	}
	
	public void agregarDiaYHorarioDeAtencion(DiaYHorarioDeAtencion unDiaYHorarioDeAtencion) {
		this.diasYHhorariosDeAtencion.add(unDiaYHorarioDeAtencion);
	}
	
	@Override
	public boolean estaCercaDe(Point unaPosicion){
		return this.getCoordenadas().distance(unaPosicion) <= this.rubro.radioDeCercania() * 0.1;
	}
	
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.diasYHhorariosDeAtencion.stream().anyMatch(
				diaYHorario -> diaYHorario.estaDentroDelDiaYHorarioDeAtencion(unDiaYHorario));
	}
}
