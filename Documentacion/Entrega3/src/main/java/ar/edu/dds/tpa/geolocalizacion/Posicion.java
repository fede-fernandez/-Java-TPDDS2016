package ar.edu.dds.tpa.geolocalizacion;

public class Posicion {
	private double longitud;
	private double latitud;

	public Posicion(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public double distanciaA(Posicion otroPunto) {
		double radioDeLaTierra = 6371;
		double latitudDelta = Math.toRadians(otroPunto.getLatitud() - latitud);
		double longitudDelta = Math.toRadians(otroPunto.getLongitud()
				- longitud);
		double a = Math.sin(latitudDelta / 2) * Math.sin(latitudDelta / 2)
				+ Math.cos(Math.toRadians(latitud))
				* Math.cos(Math.toRadians(otroPunto.getLatitud()))
				* Math.sin(longitudDelta / 2) * Math.sin(longitudDelta / 2);
		double distanciaAngular = 2 * Math
				.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return radioDeLaTierra * distanciaAngular;
	}
}
