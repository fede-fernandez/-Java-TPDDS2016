package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;

import ar.edu.dds.tpa.datastoreTest.MorphiaDatastoreTest;
import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.historial.HistorialDeBusquedaEnMongo;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.persistencia.Mapa;
import ar.edu.dds.tpa.persistencia.MapaEnBaseDeDatos;
import ar.edu.dds.tpa.persistencia.Persistible;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class POIsBusquedaController implements Persistible {
	
	private Buscador buscador;
	
	public POIsBusquedaController() {
		MorphiaDatastoreTest morphiaDatastore = MorphiaDatastoreTest.obtenerInstancia();
		Datastore dbMongo = morphiaDatastore.getDatastore();
		HistorialDeBusqueda historialBusqueda = new HistorialDeBusquedaEnMongo(dbMongo);
		Mapa mapa = new MapaEnBaseDeDatos();
		this.buscador = new Buscador(mapa, historialBusqueda);
	}
	
	public ModelAndView mostrarTerminal(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "busquedaVisualizacionPOIs/buscarPOIs.hbs");
	}
	
	public ModelAndView listarPOIS(Request req, Response res){
		Map<String, List<PuntoDeInteres>> model = new HashMap<>();
		String textoLibre = req.queryParams("textoLibre");
		String nombre = req.queryParams("nombre");
		String comuna = req.queryParams("comuna");
		Terminal terminal;
		
		List<Terminal> terminales = repositorio.traerTodos(Terminal.class);

		try {
			terminal = terminales.stream().filter(unaTerminal -> unaTerminal.getNombre().equals(nombre)
					&& unaTerminal.getComuna().getNombre().equals(comuna)).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			return new ModelAndView(null, "busquedaVisualizacionPOIs/errorTerminal.hbs");
		}

		List<PuntoDeInteres> pois = buscador.buscar(textoLibre, terminal);
		
		model.put("pois", pois);
		
		return new ModelAndView(model, "busquedaVisualizacionPOIs/mostrarResultadosPOIs.hbs");
	}
	
	
	public ModelAndView mostrarInformacionPOIs(Request req, Response res){
		Map<String, PuntoDeInteres> model = new HashMap<>();
		String id = req.params("id");
		
		PuntoDeInteres pois = repositorio.buscarPorID(PuntoDeInteres.class,Integer.parseInt(id));
		model.put("pois", pois);
		return new ModelAndView(model, "busquedaVisualizacionPOIs/mostrarPOIs.hbs");
	}

}
