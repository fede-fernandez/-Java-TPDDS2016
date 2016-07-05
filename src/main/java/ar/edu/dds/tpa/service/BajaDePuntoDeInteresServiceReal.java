package ar.edu.dds.tpa.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class BajaDePuntoDeInteresServiceReal extends ServicioExternoWeb implements BajaDePuntoDeInteresService {
	@Override
	public String obtenerPuntosDeInteresADarDeBaja() {
		Client cliente = Client.create();
		WebResource recursoWeb = cliente.resource(propiedades().obtenerValorDe("URLServicioDeBajaDePOIs"));
		return recursoWeb.get(String.class);
	}
}