package ar.edu.dds.tpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Rubro {
	
	  @Id
	  @GeneratedValue
	  private Long id;
	
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