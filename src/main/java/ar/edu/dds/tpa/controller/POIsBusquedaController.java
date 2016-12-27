package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.repository.Mapa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class POIsBusquedaController implements Persistible {

	private Buscador buscador;

	public POIsBusquedaController(HistorialDeBusqueda historial,Mapa mapa) {
		this.buscador = new Buscador(mapa, historial);
	}

	public ModelAndView mostrarTerminal(Request req, Response res) {
		String id = req.params("id");
		Terminal terminal = repositorio.buscarPorID(Terminal.class, Integer.parseInt(id));

		return new ModelAndView(terminal, "Terminal/busquedaVisualizacionPOIs/buscarPOIs.hbs");
	}

	public ModelAndView listarPOIS(Request req, Response res) {
		Map<String, List<PuntoDeInteres>> model = new HashMap<>();
		String textoLibre = req.queryParams("textoLibre");

		String id = req.queryParams("id");

		System.out.println(id);

		Terminal terminal = repositorio.buscarPorID(Terminal.class, Integer.parseInt(id));

		List<PuntoDeInteres> pois = buscador.buscar(textoLibre, terminal);

		model.put("pois", pois);

		return new ModelAndView(model, "Terminal/busquedaVisualizacionPOIs/mostrarResultadosPOIs.hbs");
	}

	public ModelAndView mostrarInformacionPOIs(Request req, Response res) {
		Map<String, PuntoDeInteres> model = new HashMap<>();
		String id = req.params("id");

		PuntoDeInteres pois = repositorio.buscarPorID(PuntoDeInteres.class, Integer.parseInt(id));
		model.put("pois", pois);
		return new ModelAndView(model, "Terminal/busquedaVisualizacionPOIs/mostrarPOIs.hbs");
	}

}
