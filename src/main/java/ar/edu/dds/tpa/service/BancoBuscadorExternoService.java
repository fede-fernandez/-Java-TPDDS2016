package ar.edu.dds.tpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.adapter.BancoServiceAdapter;
import ar.edu.dds.tpa.model.PuntoDeInteres;

public class BancoBuscadorExternoService implements BuscadorExterno {
	private BancoService servicioDeConsultaDeBancos;
	private BancoServiceAdapter adapterDeBancoService;

	public BancoBuscadorExternoService(BancoService servicioDeConsultaDeBancos,
			BancoServiceAdapter adapterDeBancoService) {
		this.servicioDeConsultaDeBancos = servicioDeConsultaDeBancos;
		this.adapterDeBancoService = adapterDeBancoService;
	}

	@Override
	public List<PuntoDeInteres> buscarPorTextoLibre(String unaFrase) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
		List<String> palabrasClave = Arrays.asList(unaFrase.split(" "));

		palabrasClave.forEach(unaPalabraClave -> puntosDeInteresEncontrados.addAll(adapterDeBancoService
				.obtenerBancosDeJSON(servicioDeConsultaDeBancos.getSucursalesBancosByNombreBanco(unaPalabraClave))));
		
		palabrasClave.forEach(unaPalabraClave -> puntosDeInteresEncontrados.addAll(adapterDeBancoService
				.obtenerBancosDeJSON(servicioDeConsultaDeBancos.getSucursalesBancosByServicio(unaPalabraClave))));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}
}