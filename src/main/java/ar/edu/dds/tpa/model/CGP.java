package ar.edu.dds.tpa.model;


import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.geolocalizacion.Poligono;

public class CGP extends PuntoDeInteresConServicios {
	
	
	private Poligono comuna;

	public CGP(String nombre, Posicion coordenadas, Poligono comuna) {
		super(nombre, coordenadas);
		this.comuna = comuna;
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return comuna.incluyeA(unaPosicion);
	}
}