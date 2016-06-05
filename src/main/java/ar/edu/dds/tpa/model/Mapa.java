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
		List<String> palabrasClave = Arrays.asList(unaFrase.split(" "));
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
		
		
		puntosDeInteresEncontrados.addAll(puntosDeInteres.stream()
				.filter(unPuntoDeInteres -> palabrasClave.stream()
						.anyMatch(unaPalabraClave -> unPuntoDeInteres.condicionDeBusqueda(unaPalabraClave)))
				.collect(Collectors.toList()));
		
		/*Si no encuentra puntos de interes localmente, se llaman a buscadores externos, se agregan los resultados localmente*/
		if (puntosDeInteresEncontrados.isEmpty()) {
			buscadoresExternos.stream()
					.forEach(unBuscador -> puntosDeInteresEncontrados.addAll(unBuscador.buscarPorTextoLibre(unaFrase)));
			puntosDeInteresEncontrados.forEach(unPuntoDeInteres -> agregarPuntoDeInteres(unPuntoDeInteres));
		}

		return puntosDeInteresEncontrados;
	}
}