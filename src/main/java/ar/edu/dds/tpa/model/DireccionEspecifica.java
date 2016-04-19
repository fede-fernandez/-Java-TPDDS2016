package ar.edu.dds.tpa.model;

public abstract class DireccionEspecifica {
	private String piso;
	private String departamento;
	private String unidad;
	private String codigoPostal;
	private String localidad;
	private String barrio;
	private String provincia;
	private String pais;
	
	public String getPiso() {
		return piso;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public String getUnidad() {
		return unidad;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public String getBarrio() {
		return barrio;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public String getPais() {
		return pais;
	}
		
}
