package ar.edu.dds.tpa.server.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.persistencia.Mapa;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class AdministracionDePOIController implements Route, Persistible {

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
		String id = request.queryMap("tipoPOIs").value();
		PuntoDeInteres poi=null;
		System. out. println(id);
		switch (id) {

	    case "0":
	    	poi = new ParadaDeColectivo(nombre, coordenadas);	break;
		case "1":
			poi = new LocalComercial(nombre, coordenadas,null);	break;
		case "2":
			poi = new Banco(nombre, coordenadas);   break;
		case "3":
			poi = new CGP(nombre, coordenadas);	break;

		}

		
		repositorio.persistir(poi);
		return new ModelAndView(null, "administracionPOI/altaPOI.hbs");
	}
}
