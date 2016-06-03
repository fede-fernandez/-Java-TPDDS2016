package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Buscador {
	private List<PuntoDeInteres> puntosDeInteres;
	
	

	public Buscador() {
		puntosDeInteres = new ArrayList<>();
	}
	

	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.add(unPuntoDeInteres);
	}

	public List<PuntoDeInteres> buscarPorTextoLibre(String unaFrase) {
		List<String> palabrasClave = Arrays.asList(unaFrase.split(" "));

		return puntosDeInteres.stream()
				.filter(elem -> palabrasClave.stream().anyMatch(palabra -> elem.condicionDeBusqueda(palabra)))
				.collect(Collectors.toList());
	}
	
}