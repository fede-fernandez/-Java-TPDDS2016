package ar.edu.dds.tpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.adapter.BancoServiceConverter;
import ar.edu.dds.tpa.model.PuntoDeInteres;

public abstract class BancoService extends BuscadorExterno {
	public abstract String getSucursalesBancosByNombreBanco(String nombreBanco);

	public abstract String getSucursalesBancosByServicio(String nombreServicio);

	public abstract String getSucursalesBancos();

	public BancoService(boolean soportaCache) {
		super(soportaCache);
		if (soportaCache) {
			establecerCacheDe("BancoService", getSucursalesBancos());
		}
	}

	@Override
	public List<PuntoDeInteres> buscarPor(List<String> palabrasDeBusqueda) {
		if (soportaCache()) {
			return buscarPuntosDeInteresDeLaCache(palabrasDeBusqueda);
		} else {
			return buscarPuntosDeInteresDelServicio(palabrasDeBusqueda);
		}
	}

	public List<PuntoDeInteres> buscarPuntosDeInteresDeLaCache(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		puntosDeInteresEncontrados
				.addAll(BancoServiceConverter.obtenerBancosDeJSON(consultarCacheDe("BancoService")).stream()
						.filter(unPuntoDeInteres -> palabrasDeBusqueda.stream()
								.anyMatch(unaPalabra -> unPuntoDeInteres.contienePalabraClave(unaPalabra)))
						.collect(Collectors.toList()));

		return puntosDeInteresEncontrados;
	}

	public List<PuntoDeInteres> buscarPuntosDeInteresDelServicio(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		palabrasDeBusqueda.forEach(unaPalabraDeBusqueda -> puntosDeInteresEncontrados.addAll(
				BancoServiceConverter.obtenerBancosDeJSON(getSucursalesBancosByNombreBanco(unaPalabraDeBusqueda))));

		palabrasDeBusqueda.forEach(unaPalabraDeBusqueda -> puntosDeInteresEncontrados.addAll(
				BancoServiceConverter.obtenerBancosDeJSON(getSucursalesBancosByServicio(unaPalabraDeBusqueda))));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}
}