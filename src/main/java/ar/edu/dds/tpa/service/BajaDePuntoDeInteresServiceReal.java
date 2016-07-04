package ar.edu.dds.tpa.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class BajaDePuntoDeInteresServiceReal implements BajaDePuntoDeInteresService {
	@Override
	public String obtenerPuntosDeInteresADarDeBaja() {
		Client cliente = Client.create();
		WebResource recursoWeb = cliente.resource("http://demo3537367.mockable.io/trash/pois");
		return recursoWeb.get(String.class);
	}
}