package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.repository.Mapa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionDePOIController {

	private Mapa unMapa;

	private Map<String, Class<? extends PuntoDeInteres>> tiposPOI = ImmutableMap.of("Todos", PuntoDeInteres.class,
			"Parada de colectivo", ParadaDeColectivo.class, "Local comercial", LocalComercial.class, "Banco",
			Banco.class, "CGP", CGP.class);

	public AdministracionDePOIController(Mapa unMapa) {
		this.unMapa = unMapa;
	}

	public ModelAndView buscar(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("tipo", tiposPOI.keySet());

		String textoBuscado = request.queryParams("textoBuscado");

		model.put("textoBuscado", textoBuscado);

		String tipoBuscado = request.queryParams("tipoPOI");
		model.put("tipoBuscado", tipoBuscado);
		model.put("poi", unMapa.obtenerPuntosDeInteresPorTipoYNombre(
				tiposPOI.getOrDefault(tipoBuscado, PuntoDeInteres.class), textoBuscado));
		return new ModelAndView(model, "Administrador/administracionPOI/consultarPOI.hbs");
	}

	public ModelAndView presentarEdicion(Request request, Response response) {
		int id = Integer.parseInt(request.params("id"));
		Object model = unMapa.obtenerPor(id);
		return new ModelAndView(model, "Administrador/administracionPOI/modificacionPOI.hbs");
	}

	public ModelAndView editar(Request request, Response response) {
		PuntoDeInteres poi = unMapa.obtenerPor(Integer.parseInt(request.params("id")));
		poi.setNombre(request.queryMap("nombre").value());
		poi.setCoordenadas(
				new Posicion(request.queryMap("longitud").doubleValue(), request.queryMap("latitud").doubleValue()));
		poi.setDireccion(request.queryMap("direccion").value());
		unMapa.modificar(poi);
		response.redirect("/puntosDeInteres");
		return null;
	}

	public ModelAndView eliminar(Request request, Response response) {
		Mapa mapa = unMapa;
		PuntoDeInteres p = mapa.obtenerPor(Integer.parseInt(request.queryParams("id")));
		mapa.remover(p);
		response.redirect("/puntosDeInteres");
		return null;
	}
}