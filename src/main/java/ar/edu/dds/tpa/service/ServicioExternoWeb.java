package ar.edu.dds.tpa.service;

import ar.edu.dds.tpa.properties.Propiedades;

public class ServicioExternoWeb {
	private Propiedades propiedades;
	
	public ServicioExternoWeb() {
		propiedades = new Propiedades("resources/ServiciosExternos.properties");
	}
	
	public Propiedades propiedades() {
		return propiedades;
	}
}