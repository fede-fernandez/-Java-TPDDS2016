package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends PuntoDeInteres {

	public ParadaDeColectivo(String nombreDeLinea, Point coordenadas) {
		super(nombreDeLinea, coordenadas);
	}
	
	@Override
	public boolean estaCercaDe(Point unaPosicion) {
		return this.getCoordenadas().distance(unaPosicion) <= 0.1;
	}
	
	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return true;
	}
	
//	@Override
//	public boolean condicionDeBusqueda(String unTexto){
//		return (this.getNombre() == unTexto) ||
//				(this.estaEtiquetadoPor(unTexto));
//	}
}
