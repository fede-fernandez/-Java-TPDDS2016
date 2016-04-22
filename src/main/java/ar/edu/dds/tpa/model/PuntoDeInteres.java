package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.Date;

import org.uqbar.geodds.Point;


public abstract class PuntoDeInteres {
	private String nombre;
	private Direccion direccion;
	private Point geolocalizacion;
	private ArrayList<Date> horariosDeAtencion = new ArrayList<Date>();

	
	public PuntoDeInteres(String nombre, Direccion direccion, Point geolocalizacion){
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

	public Point getGeolocalizacion() {
		return geolocalizacion;
	}

	public ArrayList<Date> getHorariosDeAtencion() {
		return horariosDeAtencion;
	}

	public void agregarHorarioDeAtencion(Date horarioDeAtencion) {
		this.horariosDeAtencion.add(horarioDeAtencion);
	}
	
	public Boolean estaCercaDe(Point posicionDelUsuario){
		return this.geolocalizacion.distance(posicionDelUsuario) <= 0.5;				
	}

	
	
}
