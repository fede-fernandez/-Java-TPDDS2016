package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends PuntoDeInteres {
	private Polygon comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(String nombre, Point coordenadas, String etiquetaPalabraClave, Polygon comuna) {
		super(nombre, coordenadas, etiquetaPalabraClave);
		this.comuna = comuna;

	}

	public void agregarServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	@Override
	public boolean estaCercaDe(Point unaPosicion) {
		return this.comuna.isInside(unaPosicion);
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return this.servicios.stream().anyMatch(
				servicio -> servicio.atiendeEn(unDiaYHorario));
	}

	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario,
			String nombreDelServicio) {
		return this.servicios.stream()
				.filter(servicio -> servicio.getNombre().equals(nombreDelServicio))
				.anyMatch(servicio -> servicio.atiendeEn(unDiaYHorario));
	}

	@Override
	public boolean condicionDeBusqueda(String unTexto) {
		 return (servicios
				 .stream()
				 .anyMatch(servicio -> servicio.estasIncluidoEnElNombre(unTexto))
				 ) 
				 || this.estaEtiquetadoPor(unTexto);
	}


}
