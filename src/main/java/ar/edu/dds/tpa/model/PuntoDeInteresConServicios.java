package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

public abstract class PuntoDeInteresConServicios extends PuntoDeInteres {
	private List<Servicio> servicios;

	public PuntoDeInteresConServicios(String nombre, Posicion coordenadas,String etiquetaPalabraClave) {
		super(nombre, coordenadas, etiquetaPalabraClave);
		servicios = new ArrayList<Servicio>();
	}

	public void agregarServicio(Servicio unServicio) {
		servicios.add(unServicio);
	}
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return servicios.stream().anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario, String nombreDelServicio) {
		return servicios.stream().filter(servicio -> servicio.getNombre().equalsIgnoreCase(nombreDelServicio))
				.anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}
	
	@Override
	public ArrayList<String> getEtiquetas() {
		ArrayList<String> etiquetas = super.getEtiquetas();
		servicios.stream().map(servicio -> servicio.getEtiquetas()).forEach(etiquetasServicio -> etiquetas.addAll(etiquetasServicio));
		return etiquetas;
	}
}