package ar.edu.dds.tpa.criterio;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.model.Usuario;

public class FiltradoPorComuna implements Criterio {
	
	private int comunaAFiltrar;	
	
	public FiltradoPorComuna(int comunaAFiltrar) {
		this.comunaAFiltrar = comunaAFiltrar;
	}
	
	public List<Usuario> filtrarUsuarios(List<Usuario> usuarios){
		return usuarios.stream()
				.filter(unUsuario -> unUsuario.getComuna() == comunaAFiltrar)
				.collect(Collectors.toList());		
	}

}
