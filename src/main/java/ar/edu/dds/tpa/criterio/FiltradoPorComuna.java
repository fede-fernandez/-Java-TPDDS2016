package ar.edu.dds.tpa.criterio;

import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Terminal;

public class FiltradoPorComuna implements Criterio {

	private Comuna comunaAFiltrar;

	public FiltradoPorComuna(Comuna comunaAFiltrar) {
		this.comunaAFiltrar = comunaAFiltrar;
	}

	public boolean filtrarTerminales(Terminal terminal) {
		return terminal.getComuna() == comunaAFiltrar;
	}
}