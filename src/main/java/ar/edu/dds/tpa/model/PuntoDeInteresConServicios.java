package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public abstract class PuntoDeInteresConServicios extends PuntoDeInteres {

	protected List<Servicio> servicios = new ArrayList<Servicio>();
	
	public PuntoDeInteresConServicios(String nombre, Point coordenadas) {
		super(nombre, coordenadas);
		// TODO Auto-generated constructor stub
	}

	public void agregarServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.servicios.stream()
				.anyMatch(servicio -> servicio.estaDisponibleEn(unDiaYHorario));
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario, String nombreDelServicio) {
		return this.servicios.stream()
				.filter(servicio -> servicio.getNombre().equals(nombreDelServicio))
				.anyMatch(servicio -> servicio.estaDisponibleEn(unDiaYHorario));
	}

	@Override
	public ArrayList<String> getEtiquetas(){
		ArrayList<String> etiquetas = super.getEtiquetas();
		servicios.stream().map(servicio -> servicio.getEtiquetas()).forEach(etiquetasServicio -> etiquetas.addAll(etiquetasServicio));
		return etiquetas;
	}

}
