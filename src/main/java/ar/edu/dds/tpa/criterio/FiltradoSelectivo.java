package ar.edu.dds.tpa.criterio;

import java.util.List;

import ar.edu.dds.tpa.model.Usuario;

public class FiltradoSelectivo implements Criterio{
	
	private List<String> nombresDeUsuariosAFiltrar;
	
	
	public FiltradoSelectivo(List<String> nombresDeUsuariosAFiltrar) {
		this.nombresDeUsuariosAFiltrar = nombresDeUsuariosAFiltrar;
	}


	public boolean filtrarUsuarios(Usuario usuario) {
		return nombresDeUsuariosAFiltrar.stream().anyMatch(unNombre -> unNombre == usuario.getNombre());
	}
	
	

}
