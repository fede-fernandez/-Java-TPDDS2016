package ar.edu.dds.tpa.server.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.persistencia.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class AdministracionDePOIController implements Route {

	@Override
	public Object handle(Request arg0, Response arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ModelAndView buscar(Request request, Response response){
		Map<String, Object> model = new HashMap<>();
		String textoBuscado = request.queryMap("textoBuscado").value();
		if (textoBuscado == null || textoBuscado.isEmpty()){
			return new ModelAndView(null, "administracionPOI/consultarPOI.hbs");
		}
		model.put("textoBuscado", textoBuscado);
		//model.put("poi", new Buscador(new Mapa()).buscar(textoBuscado, new Usuario()));
		return new ModelAndView(model, "administracionPOI/consultarPOI.hbs");
	}

	public ModelAndView formularioAlta(Request request, Response response){
		return new ModelAndView(null, "administracionPOI/altaPOI.hbs");
	}
	
	public ModelAndView alta(Request request, Response response){
		Map<String, Object> model  = new HashMap<>();
		String nombre = request.queryMap("nombre").value();
		Double longitud = Double.valueOf(request.queryMap("longitud").value());
		Double latitud = Double.valueOf(request.queryMap("latitud").value());
		Posicion coordenadas = new Posicion(longitud, latitud);
		//TODO: Por ahora solo crea paradas de colectivo
		PuntoDeInteres poi = new ParadaDeColectivo(nombre, coordenadas);
		new Repositorio().persistir(poi);
		return new ModelAndView(null, "administracionPOI/altaPOI.hbs");
	}
}
