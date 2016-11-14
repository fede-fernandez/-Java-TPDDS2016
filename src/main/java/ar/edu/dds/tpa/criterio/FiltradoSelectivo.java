package ar.edu.dds.tpa.criterio;

import java.util.List;

import ar.edu.dds.tpa.model.Terminal;

public class FiltradoSelectivo implements Criterio {

	private List<String> nombresDeUsuariosAFiltrar;

	public FiltradoSelectivo(List<String> nombresDeUsuariosAFiltrar) {
		this.nombresDeUsuariosAFiltrar = nombresDeUsuariosAFiltrar;
	}

	public boolean filtrarTerminales(Terminal terminal) {
		return nombresDeUsuariosAFiltrar.stream().anyMatch(unNombre -> unNombre == terminal.getNombre());
	}
}