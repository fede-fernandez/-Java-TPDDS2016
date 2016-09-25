package ar.edu.dds.tpa.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {

	private Properties propiedades;

	public Propiedades(String rutaDelArchivoProperties) {
		propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(rutaDelArchivoProperties));
		} catch (IOException excepcion) {
			throw new RuntimeException(excepcion);
		}
	}

	public String obtenerValorDe(String unaPropiedad) {
		return propiedades.getProperty(unaPropiedad);
	}
}