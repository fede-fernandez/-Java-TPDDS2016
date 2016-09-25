package ar.edu.dds.tpa.model;

import javax.persistence.*;

@Entity
public class Comuna {

	@Id
	private Integer numero;

	private String nombre;

	public Comuna() {

	}

	public Comuna(Integer numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}
}