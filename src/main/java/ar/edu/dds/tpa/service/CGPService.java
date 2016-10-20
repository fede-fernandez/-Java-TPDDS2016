package ar.edu.dds.tpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.adapter.CGPServiceConverter;
import ar.edu.dds.tpa.adapter.model.CentroDTO;
import ar.edu.dds.tpa.model.PuntoDeInteres;

public abstract class CGPService extends BuscadorExterno {

	@Override
	public List<PuntoDeInteres> buscarPor(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		palabrasDeBusqueda.forEach(unaPalabraDeBusqueda -> puntosDeInteresEncontrados
				.addAll(CGPServiceConverter.obtenerCGPsDeListaCentrosDTO(getCGPsByCalleOBarrio(unaPalabraDeBusqueda))));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}

	public abstract List<CentroDTO> getCGPsByCalleOBarrio(String calleOBarrio);
}