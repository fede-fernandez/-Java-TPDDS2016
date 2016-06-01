package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends PuntoDeInteresConServicios {
	private Polygon comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(String nombre, Point coordenadas, Polygon comuna) {
		super(nombre, coordenadas);
		this.comuna = comuna;

	}

	@Override
	public boolean estaCercaDe(Point unaPosicion) {
		return this.comuna.isInside(unaPosicion);
	}
}
