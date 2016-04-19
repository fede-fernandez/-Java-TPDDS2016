package ar.edu.dds.tpa.model;

public abstract class Direccion {
	private String calle;
	private int altura;
	private String calleCortante1;
	private String calleCortante2;
	private DireccionEspecifica direccionEspecifica;
	
	public String getCalle() {
		return calle;
	}
	public int getAltura() {
		return altura;
	}
	public String getCalleCortante1() {
		return calleCortante1;
	}
	public String getCalleCortante2() {
		return calleCortante2;
	}
	public DireccionEspecifica getDireccionEspecifica() {
		return direccionEspecifica;
	}
}
