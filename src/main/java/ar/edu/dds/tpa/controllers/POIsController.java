package ar.edu.dds.tpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.Persistible;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class POIsController implements Persistible {
	
	public ModelAndView listar(Request req, Response res){
		Map<String, List<PuntoDeInteres>> model = new HashMap<>();
		
		List<PuntoDeInteres> pois =(List<PuntoDeInteres>) repositorio.traerTodos(PuntoDeInteres.class);	
		
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
