package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.Date;


public abstract class PuntoDeInteres {
	private String nombre;
	private Direccion direccion;
	private Geolocalizacion geolocalizacion;
	private ArrayList<Date> horariosDeAtencion = new ArrayList<Date>();

	
	public PuntoDeInteres(String nombre, Direccion direccion, Geolocalizacion geolocalizacion){
		this.nombre = nombre;
		this.direccion = direccion;
		this.geolocalizacion = geolocalizacion;
	}
		
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
	
	public Boolean estaCercaDe(Geolocalizacion posicionActual){
		return this.geolocalizacion.distanciaA(posicionActual) <= 0.5;				
	}

	
	
}
