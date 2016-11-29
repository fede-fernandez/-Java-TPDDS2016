package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.Map;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController implements Persistible {

	public ModelAndView mostrarTerminales(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("comuna", repositorio.traerTodos(Comuna.class));
		model.put("terminal", repositorio.traerTodos(Terminal.class));
		return new ModelAndView(model, "GestionDeTerminales/Terminales.hbs");
	}

	public Void nuevaTerminal(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		Double longitud = Double.valueOf(request.queryParams("longitud"));
		Double latitud = Double.valueOf(request.queryParams("latitud"));
		Posicion coordenadas = new Posicion(longitud, latitud);
		Comuna comuna = repositorio.buscarPorID(Comuna.class, Integer.parseInt(request.queryParams("comuna")));

		Terminal nuevaTerminal = new Terminal(nombre, coordenadas, comuna);

		repositorio.persistir(nuevaTerminal);

		response.redirect("/terminales");

		return null;
	}

	public Void modificarTerminal(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		Double longitud = Double.valueOf(request.queryParams("longitud"));
		Double latitud = Double.valueOf(request.queryParams("latitud"));
		Posicion coordenadas = new Posicion(longitud, latitud);
		Comuna comuna = repositorio.buscarPorID(Comuna.class, Integer.parseInt(request.queryParams("comuna")));

		Terminal terminalModificada = repositorio.buscarPorID(Terminal.class,
				Integer.parseInt(request.queryParams("id")));

		terminalModificada.setNombre(nombre);
		terminalModificada.setCoordenadas(coordenadas);
		terminalModificada.setComuna(comuna);

		repositorio.persistir(terminalModificada);

		response.redirect("/terminales");

		return null;
	}

	public Void eliminarTerminal(Request request, Response response) {
		Terminal terminalAEliminar = repositorio.buscarPorID(Terminal.class,
				Integer.parseInt(request.queryParams("id")));

		repositorio.eliminar(terminalAEliminar);

		response.redirect("/terminales");

		return null;
	}
}