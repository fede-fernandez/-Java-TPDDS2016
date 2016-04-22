package ar.edu.dds.tpa.model;

import java.math.BigDecimal;

//TODO Cambiar lo de xtend a java par no usar esa dependencia.
public class Coordenada {
	BigDecimal altitud;
	BigDecimal longitud;

	public Coordenada(int altitud, int longitud) {
		this.altitud = new BigDecimal(altitud);
		this.longitud = new BigDecimal(longitud);
	}
	
	public Coordenada(double altitud, double longitud) {
		this.altitud = new BigDecimal(altitud);
		this.longitud = new BigDecimal(longitud);
	}
	
	public double altitud() {
		return this.altitud.doubleValue();
	}
	
	public double longitud() {
		return this.longitud.doubleValue();
	}
}
