package ar.edu.dds.tpa.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rubro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Double radioDeCercania;

	private String nombre;
	
	public Rubro() {
		
	}

	public Rubro(String nombre, Double radioDeCercania) {
		this.nombre = nombre;
		this.radioDeCercania = radioDeCercania;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getRadioDeCercania() {
		return radioDeCercania;
	}
}