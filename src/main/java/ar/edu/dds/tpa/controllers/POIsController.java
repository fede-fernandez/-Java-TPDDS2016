package ar.edu.dds.tpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.Persistible;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class POIsController implements Persistible {
	
	public ModelAndView mostrarTerminal(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		String usuario = req.params("usuario");
		
		model.put("usuario", usuario);
		return new ModelAndView(model, "pois/buscarPOIs.hbs");
	}
	
	public ModelAndView listar(Request req, Response res){
		Map<String, List<PuntoDeInteres>> model = new HashMap<>();
		String textoLibre = req.queryParams("textoLibre");
		
		List<PuntoDeInteres> pois =(List<PuntoDeInteres>) repositorio.buscarTextoLibre(textoLibre, null);
		
		model.put("pois", pois);
		
		return new ModelAndView(model, "pois/mostrarResultadosPOIs.hbs");
	}
	
	
	public ModelAndView mostrar(Request req, Response res){
		Map<String, PuntoDeInteres> model = new HashMap<>();
		String id = req.params("id");
		
		PuntoDeInteres pois = repositorio.buscarPorID(PuntoDeInteres.class,Integer.parseInt(id));
		model.put("pois", pois);
		return new ModelAndView(model, "pois/mostrarPOIs.hbs");
	}

}
