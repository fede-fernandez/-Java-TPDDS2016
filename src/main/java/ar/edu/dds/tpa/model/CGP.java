package ar.edu.dds.tpa.model;

import org.uqbar.geodds.*;;

public class CGP extends PuntoDeInteres {
	
	private Comuna comuna;
	
	public CGP(String nombre, Direccion direccion, Point geolocalizacion, Comuna comuna){
		super(nombre,direccion,geolocalizacion);
		this.comuna = comuna;
	}
	
	public Boolean estaCercaDe(Point posicionDelUsuario){		
		
		return this.comuna.estaEnComuna(posicionDelUsuario);				
	}

}
