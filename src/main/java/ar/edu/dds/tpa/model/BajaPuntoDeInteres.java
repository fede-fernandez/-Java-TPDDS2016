package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.geolocalizacion.Posicion;;

public class BajaPuntoDeInteres {

	private String nombre;
	private double x;
	private double y;
	private int dia;
	private int mes;
	private int anio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Posicion getCoordenadas() {
		return new Posicion(x, y);
	}

	public LocalDateTime getFechaBaja() {
		return LocalDateTime.of(anio, mes, dia, 0, 0, 0);
	}

	public BajaPuntoDeInteres() {
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public boolean equals(PuntoDeInteres poi) {
		return this.getCoordenadas().distanciaA(poi.getCoordenadas()) < 0.0000002
				&& this.getNombre().equalsIgnoreCase(poi.getNombre());
	}
}