package ar.edu.dds.tpa.model;

import org.uqbar.geodds.Point;

public class Geolocalizacion {
	
	private Point coordenadas;
	
	public Geolocalizacion(double latitud, double longitud){		
		this.coordenadas = new Point(latitud,longitud);
	}	
	
	public Point getCoordenadas() {
		return coordenadas;
	}

	
		
	public double distanciaA(Geolocalizacion unPunto){
		return coordenadas.distance(unPunto.getCoordenadas());		
	}
	
}
