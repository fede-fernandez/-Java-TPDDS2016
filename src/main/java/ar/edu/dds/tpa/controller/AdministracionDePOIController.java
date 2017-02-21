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

		String textoBuscado = request.queryMap("textoBuscado").value();

		model.put("textoBuscado", textoBuscado);

		String tipoBuscado = request.queryMap("tipoPOI").value();
		model.put("tipoBuscado", tipoBuscado);
		model.put("poi", unMapa.obtenerPuntosDeInteresPorTipoYNombre(
				tiposPOI.getOrDefault(tipoBuscado, PuntoDeInteres.class), textoBuscado));
		return new ModelAndView(model, "Administrador/administracionPOI/consultarPOI.hbs");
	}

	public ModelAndView formularioAlta(Request request, Response response) {
		return new ModelAndView(null, "Administrador/administracionPOI/modificacionPOI.hbs");
	}

	public ModelAndView alta(Request request, Response response) {
		String nombre = request.queryMap("nombre").value();
		Double longitud = Double.valueOf(request.queryMap("longitud").value());
		Double latitud = Double.valueOf(request.queryMap("latitud").value());
		Posicion coordenadas = new Posicion(longitud, latitud);
		String id = request.queryMap("tipoPOIs").value();
		PuntoDeInteres poi = null;
		switch (id) {

		case "0":
			poi = new ParadaDeColectivo(nombre, coordenadas, null);
			break;
		case "1":
			poi = new LocalComercial(nombre, coordenadas, null, null);
			break;
		case "2":
			poi = new Banco(nombre, coordenadas, null);
			break;
		case "3":
			poi = new CGP(nombre, coordenadas, null);
			break;

		}

		unMapa.agregar(poi);
		return new ModelAndView(null, "Administrador/administracionPOI/modificacionPOI.hbs");
	}

	public ModelAndView presentarEdicion(Request request, Response response) {
		int id = Integer.parseInt(request.params(":poi"));
		Object model = unMapa.obtenerPor(id);
		return new ModelAndView(model, "Administrador/administracionPOI/modificacionPOI.hbs");
	}

	public ModelAndView editar(Request request, Response response) {
		PuntoDeInteres poi = unMapa.obtenerPor(request.queryMap("id").integerValue());
		poi.setNombre(request.queryMap("nombre").value());
		poi.setCoordenadas(
				new Posicion(request.queryMap("longitud").doubleValue(), request.queryMap("latitud").doubleValue()));
		poi.setDireccion(request.queryMap("direccion").value());
		unMapa.modificar(poi);
		response.redirect("/administracion/consultar");
		return null;
	}

	public ModelAndView eliminar(Request request, Response response) {
		Mapa mapa = unMapa;
		PuntoDeInteres p = mapa.obtenerPor(request.queryMap("id").integerValue());
		mapa.remover(p);
		response.redirect("/administracion/consultar");
		return null;
	}
}