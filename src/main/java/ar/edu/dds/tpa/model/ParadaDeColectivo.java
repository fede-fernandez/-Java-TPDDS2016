package ar.edu.dds.tpa.model;

import org.uqbar.geodds.*;

public class ParadaDeColectivo extends PuntoDeInteres {
	
	public ParadaDeColectivo(String nombre, Direccion direccion, Point geolocalizacion){
		super(nombre,direccion,geolocalizacion);
	}
	
	
	@Override
	public Boolean estaCercaDe(Point posicionDelUsuario) {
		return this.getGeolocalizacion().distance(posicionDelUsuario) <= 0.1;
	}

}
