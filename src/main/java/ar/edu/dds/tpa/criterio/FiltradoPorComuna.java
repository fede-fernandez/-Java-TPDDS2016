package ar.edu.dds.tpa.criterio;

import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.model.Comuna;

public class FiltradoPorComuna implements Criterio {
	
	private Comuna comunaAFiltrar;

	public FiltradoPorComuna(Comuna comunaAFiltrar) {
		this.comunaAFiltrar = comunaAFiltrar;
	}

	public boolean filtrarUsuarios(Usuario usuario) {
		return usuario.getComuna() == comunaAFiltrar;
	}
}