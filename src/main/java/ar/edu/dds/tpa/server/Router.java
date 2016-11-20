package ar.edu.dds.tpa.server;


import ar.edu.dds.tpa.controller.*;
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
		
		Spark.get("/pois", poisController::listarPOIS, engine);
		Spark.get("/pois/:id", poisController::mostrarInformacionPOIs, engine);
		Spark.get("/terminal", poisController::mostrarTerminal, engine);
		
		Spark.get("/administracion/consultar", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/administracion/consultar/:tipo/:nombre", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/administracion/editar/:poi", new AdministracionDePOIController()::presentarEdicion, engine);
		Spark.post("/administracion/editar", new AdministracionDePOIController()::editar, engine);
		Spark.post("/administracion/eliminar", new AdministracionDePOIController()::eliminar, engine);
		
		Spark.get("/gestionDeTerminales", new TerminalController()::mostrarTerminales, engine);
		
	}

}
