package ar.edu.dds.tpa.model;

import org.uqbar.geodds.*;;

public class CGP extends PuntoDeInteres {
	
	private Polygon comuna;
	
	public CGP(String nombre, Direccion direccion, Point geolocalizacion, Polygon comuna){
		super(nombre,direccion,geolocalizacion);
		this.comuna = comuna;
	}
	
	public Boolean estaCercaDe(Point posicionDelUsuario){		
		
		return this.comuna.isInside(posicionDelUsuario);				
	}

}
