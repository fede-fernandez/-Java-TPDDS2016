package ar.edu.dds.tpa.criterio;

import java.util.List;


import ar.edu.dds.tpa.model.Usuario;

public interface Criterio {
	
	public List<Usuario> filtrarUsuarios(List<Usuario> usuarios);
	


}
