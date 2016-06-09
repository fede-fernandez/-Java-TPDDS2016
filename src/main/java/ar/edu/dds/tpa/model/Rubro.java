package ar.edu.dds.tpa.model;

public class Rubro {
	
	
	private double radioDeCercania;
	private String nombre;
	
	public Rubro(double radioDeCercania, String nombre) {	
		this.radioDeCercania = radioDeCercania;
		this.nombre = nombre;
	}

	public double getRadioDeCercania() {
		return radioDeCercania;
	}

	public String getNombre() {
		return nombre;
	}



}