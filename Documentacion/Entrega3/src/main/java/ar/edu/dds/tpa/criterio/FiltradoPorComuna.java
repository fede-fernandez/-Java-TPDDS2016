package ar.edu.dds.tpa.criterio;

import ar.edu.dds.tpa.model.Usuario;


public class FiltradoPorComuna implements Criterio {
	
	private int comunaAFiltrar;	
	
	public FiltradoPorComuna(int comunaAFiltrar) {
		this.comunaAFiltrar = comunaAFiltrar;
	}
	
	public boolean filtrarUsuarios(Usuario usuario){
		return usuario.getComuna() == comunaAFiltrar;		
	}

}
