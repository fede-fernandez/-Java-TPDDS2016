package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.service.BuscadorExterno;

public class Mapa {
	private List<PuntoDeInteres> puntosDeInteres;
	private List<BuscadorExterno> buscadoresExternos;

	public Mapa() {
		puntosDeInteres = new ArrayList<>();
		buscadoresExternos = new ArrayList<>();
	}

	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.add(unPuntoDeInteres);
	}

	public void sacarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.remove(unPuntoDeInteres);
	}

	public void modificarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado) {
		puntosDeInteres.remove(unPuntoDeInteres);
		puntosDeInteres.add(puntoDeInteresModificado);
	}

	public void agregarBuscadorExterno(BuscadorExterno unBuscadorExterno) {
		buscadoresExternos.add(unBuscadorExterno);
	}

	public List<PuntoDeInteres> buscarPorTextoLibre(String unaFrase) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
		List<String> palabrasDeFrase = Arrays.asList(unaFrase.split(" "));
		puntosDeInteresEncontrados.addAll(puntosDeInteres.stream()
				.filter(unPuntoDeInteres -> palabrasDeFrase.stream()
						.anyMatch(unaPalabra -> unPuntoDeInteres.contienePalabraClave(unaPalabra)))
				.collect(Collectors.toList()));

		buscadoresExternos.stream()
				.forEach(unBuscador -> puntosDeInteresEncontrados.addAll(unBuscador.buscarPorTextoLibre(unaFrase)));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}
}