package ar.edu.dds.tpa.model;

public class Direccion {
	private String calle;
	private int altura;
	private String calleCortante1;
	private String calleCortante2;
	private DireccionEspecifica direccionEspecifica;
	
	public Direccion(String calle, int altura){
		this.calle = calle;
		this.altura = altura;
	}
	
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
