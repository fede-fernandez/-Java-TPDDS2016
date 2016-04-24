package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Rubro {
	
	private String nombreRubro;
	
	public Rubro(String nombreRubro) {
		super();
		this.nombreRubro = nombreRubro;
	}

	public abstract double radioDeCercania();

	public String getNombreRubro() {
		return nombreRubro;
	}
	
	public ArrayList<String> getEtiquetas(){
		ArrayList<String> etiquetas = new ArrayList<String>();
		etiquetas.addAll(Arrays.asList(this.nombreRubro.split(" ")));
		return etiquetas;
	}
}
