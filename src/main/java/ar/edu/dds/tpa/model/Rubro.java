package ar.edu.dds.tpa.model;

public abstract class Rubro {
	
	private String nombreRubro;
	
	public Rubro(String nombreRubro) {
		super();
		this.nombreRubro = nombreRubro;
	}

	public abstract double radioDeCercania();

	public String getNombreRubro() {
		return nombreRubro;
	}
}
