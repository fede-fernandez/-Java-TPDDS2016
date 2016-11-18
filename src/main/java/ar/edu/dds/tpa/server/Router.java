package ar.edu.dds.tpa.server;


import ar.edu.dds.tpa.controller.*;
import ar.edu.dds.tpa.server.controllers.AdministracionDePOIController;
import ar.edu.dds.tpa.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.build();

		Spark.staticFiles.location("/public");
		
		POIsBusquedaController poisController = new POIsBusquedaController();
		
		Spark.get("/poisEncontrados", poisController::listarPOIS, engine);
		Spark.get("/pois/:id", poisController::mostrarInformacionPOIs, engine);
		Spark.get("/terminal", poisController::mostrarTerminal, engine);
		
		Spark.get("/consultarPOI", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/consultarPOI/:textoBuscado", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/altaPOI", new AdministracionDePOIController()::formularioAlta, engine);
		Spark.post("/altaPOI", new AdministracionDePOIController()::alta, engine);
		
		Spark.get("/gestionDeTerminales", new TerminalController()::mostrarTerminales, engine);
		
	}

}
