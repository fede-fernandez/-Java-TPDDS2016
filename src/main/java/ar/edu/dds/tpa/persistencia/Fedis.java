package ar.edu.dds.tpa.persistencia;

import java.util.HashMap;
import java.util.Map;

public class Fedis implements RepositorioCache {
	private Map<String, String> datos;

	public Fedis() {
		datos = new HashMap<String, String>();
	}
	
	public String consultarValorPor(String unaClave) {
		return datos.get(unaClave);
	}
	
	public void insertar(String unaClave, String unValor) {
		datos.put(unaClave, unValor);
	}	
}