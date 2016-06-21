package ar.edu.dds.tpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.adapter.CGPServiceAdapter;
import ar.edu.dds.tpa.model.PuntoDeInteres;

public class CGPBuscadorExternoService implements BuscadorExterno {
	private CGPService servicioDeConsultaDeCGPs;
	private CGPServiceAdapter adapterDeCGPService;

	public CGPBuscadorExternoService(CGPService servicioDeConsultaDeCGPs, CGPServiceAdapter adapterDeCGPService) {
		this.servicioDeConsultaDeCGPs = servicioDeConsultaDeCGPs;
		this.adapterDeCGPService = adapterDeCGPService;
	}

	@Override
	public List<PuntoDeInteres> buscarPorTextoLibre(String unaFrase) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
		List<String> palabrasClave = Arrays.asList(unaFrase.split(" "));

		palabrasClave.forEach(unaPalabraClave -> puntosDeInteresEncontrados.addAll(adapterDeCGPService
				.obtenerCGPsDeListaCentrosDTO(servicioDeConsultaDeCGPs.getCGPsByCalleOBarrio(unaPalabraClave))));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}
}