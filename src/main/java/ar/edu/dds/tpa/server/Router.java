package ar.edu.dds.tpa.server;


import ar.edu.dds.tpa.controllers.POIsController;
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
		
		POIsController poisController = new POIsController();
		
		Spark.get("/poisEncontrados", poisController::listar, engine);
		Spark.get("/pois/:id", poisController::mostrar, engine);
		Spark.get("/terminal/:usuario", poisController::mostrarTerminal, engine);
		Spark.get("/consultarPOI", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/consultarPOI/:textoBuscado", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/altaPOI", new AdministracionDePOIController()::formularioAlta, engine);
		Spark.post("/altaPOI", new AdministracionDePOIController()::alta, engine);

	}

}
