package ar.edu.dds.tpa.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	private Properties propiedades;

	public Propiedades(String rutaDelArchivoProperties) {
		propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(rutaDelArchivoProperties));
		} catch (FileNotFoundException error) {
			System.out.println("Se produjo un error al cargar las propiedades.");
		} catch (IOException error) {
			System.out.println("Se produjo un error al leer las propiedades.");
		}
	}

	public String obtenerValorDe(String unaPropiedad) {
		return propiedades.getProperty(unaPropiedad);
	}
}