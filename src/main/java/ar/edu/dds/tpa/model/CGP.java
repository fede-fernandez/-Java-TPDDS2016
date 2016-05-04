package ar.edu.dds.tpa.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.geolocalizacion.Poligono;

public class CGP extends PuntoDeInteres {
	
	
	private Poligono comuna;
	private List<Servicio> servicios;

	public CGP(String nombre, Posicion coordenadas, Poligono comuna) {
		super(nombre, coordenadas);
		this.comuna = comuna;
		this.servicios = new ArrayList<Servicio>();
	}

	public void agregarServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return this.comuna.incluyeA(unaPosicion);
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.servicios.stream().anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario, String nombreDelServicio) {
		return this.servicios.stream().filter(servicio -> servicio.getNombre().equals(nombreDelServicio))
				.anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}
	
	@Override
	public ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = super.getEtiquetas();
		servicios.stream().map(servicio -> servicio.getEtiquetas()).forEach(etiquetasServicio -> etiquetas.addAll(etiquetasServicio));
		return etiquetas;
	}
}