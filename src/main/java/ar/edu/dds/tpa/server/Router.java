package ar.edu.dds.tpa.server;


import ar.edu.dds.tpa.controllers.POIsController;
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
		
		Spark.get("/hello", (req, res) -> "Hello World");
		Spark.get("/poisEncontrados", poisController::listar, engine);
		Spark.get("/:id", poisController::mostrar, engine);

	}

}
