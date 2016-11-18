package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {
	public ModelAndView mostrarTerminales(Request req, Response res){
		//Map<String, List<Terminal>> model = new HashMap<>();
		
		//return new ModelAndView(model, "GestionDeTerminales/BuscarTerminales.hbs");
		return new ModelAndView(null, "GestionDeTerminales/BuscarTerminales.hbs");
	}
}