package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.Date;


public abstract class PuntoDeInteres {
	private String nombre;
	private Direccion direccion;
	private Geolocalizacion geolocalizacion;
	private ArrayList<Date> horariosDeAtencion = new ArrayList<Date>();

		
	public String getNombre() {
		return nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public Geolocalizacion getGeolocalizacion() {
		return geolocalizacion;
	}

	public ArrayList<Date> getHorariosDeAtencion() {
		return horariosDeAtencion;
	}

	public void agregarHorarioDeAtencion(Date horarioDeAtencion) {
		this.horariosDeAtencion.add(horarioDeAtencion);
	}
}
