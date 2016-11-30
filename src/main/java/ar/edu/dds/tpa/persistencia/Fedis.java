package ar.edu.dds.tpa.persistencia;

import java.util.HashMap;
import java.util.Map;

public class Fedis implements RepositorioCache {
	
	private static Fedis instancia;
	private Map<String, String> datos;

	private Fedis() {
		datos = new HashMap<String, String>();
	}
	
	public static Fedis obtenerConexion() {
		if(instancia == null) {
			instancia = new Fedis();
		}
		return instancia;
	}
	
	public String consultarValorPor(String unaClave) {
		return datos.get(unaClave);
	}
	
	public void insertar(String unaClave, String unValor) {
		datos.put(unaClave, unValor);
	}	
}