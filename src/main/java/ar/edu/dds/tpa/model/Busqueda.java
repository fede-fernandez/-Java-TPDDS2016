package ar.edu.dds.tpa.model;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class Busqueda {
	
	private List<PuntoDeInteres> puntosDeInteres = new ArrayList<>();
	
	
	public void agregarPuntoInteres(PuntoDeInteres UnPunto) {
		puntosDeInteres.add(UnPunto);
	}
	
	public List<PuntoDeInteres> buscarPorTextoLibre(String unTexto){

		return puntosDeInteres.stream()
    				.filter(elem -> elem.condicionDeBusqueda(unTexto))
    				.collect(Collectors.toList());
	}
		
}


	

