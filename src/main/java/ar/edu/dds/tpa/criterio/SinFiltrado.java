package ar.edu.dds.tpa.criterio;

import ar.edu.dds.tpa.model.Usuario;

public class SinFiltrado implements Criterio {

	public boolean filtrarUsuarios(Usuario usuario) {
		return true;
	}
}