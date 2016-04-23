package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class LocalComercial extends PuntoDeInteres {
	private Rubro rubro;
	private List<DiaYHorarioDeAtencion> diasYHorariosDeAtencion = new ArrayList<DiaYHorarioDeAtencion>();
	
	public LocalComercial(String nombre, Point coordenadas, Rubro rubro,String etiquetaPalabraClave) {
		super(nombre, coordenadas,etiquetaPalabraClave);
		this.rubro = rubro;
	}

	public Rubro getRubro() {
		return rubro;
	}
	
	public void agregarDiaYHorarioDeAtencion(DiaYHorarioDeAtencion unDiaYHorarioDeAtencion) {
		this.diasYHorariosDeAtencion.add(unDiaYHorarioDeAtencion);
	}
	
	@Override
	public boolean estaCercaDe(Point unaPosicion){
		return this.getCoordenadas().distance(unaPosicion) <= this.rubro.radioDeCercania() * 0.1;
	}
	
	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.diasYHorariosDeAtencion.stream().anyMatch(
				diaYHorario -> diaYHorario.estaDentroDelDiaYHorarioDeAtencion(unDiaYHorario));
	}

	@Override
	public boolean condicionDeBusqueda(String unTexto) {
		return rubro.getNombreRubro() == unTexto
				|| this.estaEtiquetadoPor(unTexto);
	}
	

}
