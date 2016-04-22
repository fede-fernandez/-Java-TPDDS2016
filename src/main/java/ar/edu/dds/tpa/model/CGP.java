package ar.edu.dds.tpa.model;

public class CGP extends PuntoDeInteres {
	
	public CGP(String nombre, Direccion direccion, Geolocalizacion geolocalizacion){
		super(nombre,direccion,geolocalizacion);
	}
	
	public Boolean estaCercaDe(Geolocalizacion posicionDelUsuario, Comuna comuna){		
		
		//return estaEnLaComuna()
		return true;		
	}

}
