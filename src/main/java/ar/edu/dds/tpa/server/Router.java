package ar.edu.dds.tpa.server;

import ar.edu.dds.tpa.controller.AdministracionDePOIController;
import ar.edu.dds.tpa.controller.HistoricoDeConsultasController;
import ar.edu.dds.tpa.controller.LoginController;
import ar.edu.dds.tpa.controller.POIsBusquedaController;
import ar.edu.dds.tpa.controller.TerminalController;
import ar.edu.dds.tpa.persistencia.MorphiaDatastoreMock;
import ar.edu.dds.tpa.persistencia.repository.Mapa;
import ar.edu.dds.tpa.persistencia.repository.MapaEnBaseDeDatos;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusquedaEnMongo;
import ar.edu.dds.tpa.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		Spark.staticFiles.location("/public");

		HistorialDeBusqueda historial = new HistorialDeBusquedaEnMongo(
				MorphiaDatastoreMock.obtenerInstancia().getDatastore());
		Mapa mapa = new MapaEnBaseDeDatos();

		POIsBusquedaController poisController = new POIsBusquedaController(historial, mapa);
		HistoricoDeConsultasController historicoController = new HistoricoDeConsultasController(historial);
		TerminalController terminalController = new TerminalController();
		AdministracionDePOIController poisAdministracionController = new AdministracionDePOIController(mapa);

		LoginController loginController = new LoginController();

		Spark.get("/terminal/lugares", poisController::listarPOIS, engine);
		Spark.get("/terminal/lugares/:id", poisController::mostrarInformacionPOIs, engine);
		Spark.get("/terminal/:id", poisController::mostrarTerminal, engine);

		Spark.get("/puntosDeInteres", poisAdministracionController::buscar, engine);
		Spark.get("/puntosDeInteres/buscar", poisAdministracionController::buscar, engine);
		Spark.post("/puntosDeInteres/modificar/:id", poisAdministracionController::editar, engine);
		Spark.get("/puntosDeInteres/modificar/:id", poisAdministracionController::presentarEdicion, engine);
		Spark.post("/puntosDeInteres/eliminar", poisAdministracionController::eliminar, engine);

		Spark.get("/terminales", terminalController::mostrarTerminales, engine);
		Spark.post("/nuevaTerminal", terminalController::nuevaTerminal);
		Spark.post("/eliminarTerminal", terminalController::eliminarTerminal);
		Spark.post("/modificarTerminal", terminalController::modificarTerminal);

		Spark.get("/historico", historicoController::mostrarFiltrosDeHistorial, engine);
		Spark.get("/historico/filtro", historicoController::filtrarPOIs, engine);
		Spark.get("/historico/busqueda/:id", historicoController::mostrarPOIsEncontradosDeUnaBusqueda, engine);

		Spark.get("/login", loginController::mostrarLogin, engine);
		Spark.post("/login", loginController::login, engine);

	}

}