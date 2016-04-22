package ar.edu.dds.tpa.model;

public class LocalComercial extends PuntoDeInteres {
	
	private Rubro rubro;	
	
	public Rubro getRubro() {
		return rubro;
	}
	
	
	public LocalComercial(String nombre, Direccion direccion, Geolocalizacion geolocalizacion, Rubro rubro){
		super(nombre,direccion,geolocalizacion);
		this.rubro = rubro;
	}
	
	@Override
	public Boolean estaCercaDe(Geolocalizacion posicionDelUsuario){
		return this.getGeolocalizacion().distanciaA(posicionDelUsuario) <= this.rubro.radioDeCercania() * 0.1;
	}

}
