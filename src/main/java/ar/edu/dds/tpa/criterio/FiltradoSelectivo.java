package ar.edu.dds.tpa.criterio;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.model.Usuario;

public class FiltradoSelectivo implements Criterio{
	
	private List<String> nombresDeUsuariosAFiltrar;
	
	
	public FiltradoSelectivo(List<String> nombresDeUsuariosAFiltrar) {
		this.nombresDeUsuariosAFiltrar = nombresDeUsuariosAFiltrar;
	}


	public List<Usuario> filtrarUsuarios(List<Usuario> usuarios) {
		return usuarios.stream()				
				.filter(unUsuario -> nombresDeUsuariosAFiltrar.contains(unUsuario.getNombre()))
				.collect(Collectors.toList());
	}
	
	

}
