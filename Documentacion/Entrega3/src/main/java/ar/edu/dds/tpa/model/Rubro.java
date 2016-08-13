package ar.edu.dds.tpa.model;

public class Rubro {
	private double radioDeCercania;
	private String nombre;

	public Rubro(String nombre, double radioDeCercania) {
		this.nombre = nombre;
		this.radioDeCercania = radioDeCercania;
	}

	public String getNombre() {
		return nombre;
	}

	public double getRadioDeCercania() {
		return radioDeCercania;
	}
}