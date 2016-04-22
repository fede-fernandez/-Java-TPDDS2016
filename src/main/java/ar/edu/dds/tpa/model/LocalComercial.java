package ar.edu.dds.tpa.model;

import org.uqbar.geodds.*;

public class LocalComercial extends PuntoDeInteres {
	
	private Rubro rubro;	
	
	public Rubro getRubro() {
		return rubro;
	}
	
	
	public LocalComercial(String nombre, Direccion direccion, Point geolocalizacion, Rubro rubro){
		super(nombre,direccion,geolocalizacion);
		this.rubro = rubro;
	}
	
	@Override
	public Boolean estaCercaDe(Point posicionDelUsuario){
		return this.getGeolocalizacion().distance(posicionDelUsuario) <= this.rubro.radioDeCercania() * 0.1;
		//distance devuelve la distancia en kilometros
	}

}
