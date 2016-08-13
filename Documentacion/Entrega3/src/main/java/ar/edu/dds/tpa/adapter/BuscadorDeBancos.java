package ar.edu.dds.tpa.adapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.dds.tpa.adapter.model.BancoDelServicio;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.service.BancoService;

public class BuscadorDeBancos implements BuscadorExterno {
	private BancoService servicioDeConsultaDeBancos;

	public BuscadorDeBancos(BancoService servicioDeConsultaDeBancos) {
		this.servicioDeConsultaDeBancos = servicioDeConsultaDeBancos;
	}

	@Override
	public List<PuntoDeInteres> buscarPuntosDeInteresDelServicio(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		palabrasDeBusqueda.forEach(unaPalabraDeBusqueda -> puntosDeInteresEncontrados.addAll(obtenerBancosDeJSON(
				servicioDeConsultaDeBancos.getSucursalesBancosByNombreBanco(unaPalabraDeBusqueda))));

		palabrasDeBusqueda.forEach(unaPalabraDeBusqueda -> puntosDeInteresEncontrados.addAll(
				obtenerBancosDeJSON(servicioDeConsultaDeBancos.getSucursalesBancosByServicio(unaPalabraDeBusqueda))));

		return puntosDeInteresEncontrados.stream().distinct().collect(Collectors.toList());
	}

	public List<Banco> obtenerBancosDeJSON(String bancosEnJSON) {
		List<Banco> bancos = new ArrayList<Banco>();
		if (!bancosEnJSON.equals("[]")) {
			List<BancoDelServicio> bancosDelServicio = new ArrayList<BancoDelServicio>();
			Type listaDeBancos = new TypeToken<List<BancoDelServicio>>(){}.getType();
			Gson gson = new Gson();
			bancosDelServicio = gson.fromJson(bancosEnJSON, listaDeBancos);
			bancosDelServicio.stream()
					.forEach(unBancoDelServicio -> bancos.add(convertirBancoDelServicioABanco(unBancoDelServicio)));
		}
		return bancos;
	}

	public Banco convertirBancoDelServicioABanco(BancoDelServicio bancoDelServicio) {
		Banco banco = new Banco(bancoDelServicio.getBanco(),
				new Posicion(bancoDelServicio.getX(), bancoDelServicio.getY()));
		bancoDelServicio.getServicios().stream().forEach(unServicio -> banco.agregarServicio(new Servicio(unServicio)));
		return banco;
	}
}