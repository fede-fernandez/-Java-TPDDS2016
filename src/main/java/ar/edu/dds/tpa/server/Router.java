package ar.edu.dds.tpa.server;

import ar.edu.dds.tpa.controller.AdministracionDePOIController;
import ar.edu.dds.tpa.controller.HistoricoDeConsultasController;
import ar.edu.dds.tpa.controller.LoginController;
import ar.edu.dds.tpa.controller.POIsBusquedaController;
import ar.edu.dds.tpa.controller.TerminalController;
import ar.edu.dds.tpa.datastoreTest.MorphiaDatastoreTest;
import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.historial.HistorialDeBusquedaEnMongo;
import ar.edu.dds.tpa.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");

		POIsBusquedaController poisController = new POIsBusquedaController();
		HistorialDeBusqueda historial = new HistorialDeBusquedaEnMongo(
				MorphiaDatastoreTest.obtenerInstancia().getDatastore());
		HistoricoDeConsultasController historicoController = new HistoricoDeConsultasController(historial);

		LoginController loginController = new LoginController();

		Spark.get("/terminal/lugares", poisController::listarPOIS, engine);
		Spark.get("/terminal/lugares/:id", poisController::mostrarInformacionPOIs, engine);
		Spark.get("/terminal/:id", poisController::mostrarTerminal, engine);

		Spark.get("/administracion/consultar", new AdministracionDePOIController()::buscar, engine);
		Spark.get("/administracion/consultar/:tipo/:nombre", new AdministracionDePOIController()::buscar, engine);
		Spark.post("/administracion/editar/:poi", new AdministracionDePOIController()::editar, engine);
		Spark.get("/administracion/editar/:poi", new AdministracionDePOIController()::presentarEdicion, engine);
		Spark.post("/administracion/eliminar", new AdministracionDePOIController()::eliminar, engine);

		TerminalController terminalController = new TerminalController();

		Spark.get("/terminales", terminalController::mostrarTerminales, engine);
		Spark.post("/nuevaTerminal", terminalController::nuevaTerminal);
		Spark.post("/eliminarTerminal", terminalController::eliminarTerminal);
		Spark.post("/modificarTerminal", terminalController::modificarTerminal);

		Spark.get("/historico", historicoController::listarPoisConsultados, engine);
		Spark.get("/historico/:id", historicoController::mostrarPOIsEncontradosDeUnaBusqueda, engine);

		Spark.get("/login", loginController::mostrarLogin, engine);
		Spark.post("/login", loginController::login, engine);

	}

}