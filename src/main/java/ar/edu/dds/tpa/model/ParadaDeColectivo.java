package ar.edu.dds.tpa.model;

public class ParadaDeColectivo extends PuntoDeInteres {
	
	public ParadaDeColectivo(String nombre, Direccion direccion, Geolocalizacion geolocalizacion){
		super(nombre,direccion,geolocalizacion);
	}
	
	
	@Override
	public Boolean estaCercaDe(Geolocalizacion posicionDelUsuario) {
		return this.getGeolocalizacion().distanciaA(posicionDelUsuario) <= 0.1;
	}

}
