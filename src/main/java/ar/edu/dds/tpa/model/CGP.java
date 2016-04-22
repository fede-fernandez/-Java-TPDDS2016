package ar.edu.dds.tpa.model;

public class CGP extends PuntoDeInteres {
	
	private Comuna comuna;
	
	public CGP(String nombre, Direccion direccion, Geolocalizacion geolocalizacion, Comuna comuna){
		super(nombre,direccion,geolocalizacion);
		this.comuna = comuna;
	}
	
	public Boolean estaCercaDe(Geolocalizacion posicionDelUsuario){		
		
		return this.comuna.estaEnComuna(posicionDelUsuario.getCoordenadas());				
	}

}
