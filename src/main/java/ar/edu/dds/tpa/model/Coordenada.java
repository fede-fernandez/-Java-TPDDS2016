package ar.edu.dds.tpa.model;

//TODO Cambiar lo de xtend a java para no usar esa dependencia.
public class Coordenada {
	double latitud;
	double longitud;

	public Coordenada(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public double distanciaA(Coordenada otroPunto) {
		double radioDeLaTierra = 6371;
		double latitudDelta = Math.toRadians(otroPunto.getLatitud() - latitud);
		double longitudDelta = Math.toRadians(otroPunto.getLongitud()
				- longitud);
		double a = Math.sin(latitudDelta / 2) * Math.sin(latitudDelta / 2)
				+ Math.cos(Math.toRadians(latitud))
				* Math.cos(Math.toRadians(otroPunto.latitud))
				* Math.sin(longitudDelta / 2) * Math.sin(longitudDelta / 2);
		double distanciaAngular = 2 * Math
				.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return radioDeLaTierra * distanciaAngular;
	}
}
