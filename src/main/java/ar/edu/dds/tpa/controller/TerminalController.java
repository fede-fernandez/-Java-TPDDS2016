package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.Map;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.repository.ComunaRepository;
import ar.edu.dds.tpa.persistencia.repository.TerminalRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController implements Persistible {

	TerminalRepository terminalRepository = new TerminalRepository();
	ComunaRepository comunaRepository = new ComunaRepository();

	public ModelAndView mostrarTerminales(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("comuna", comunaRepository.obtenerComunas());
		model.put("terminal", terminalRepository.obtenerTerminales());
		return new ModelAndView(model, "GestionDeTerminales/Terminales.hbs");
	}

	public Void nuevaTerminal(Request request, Response response) {
		Terminal nuevaTerminal = construirTerminalAPartirDeParametros(request);
		terminalRepository.agregarTerminal(nuevaTerminal);

		response.redirect("/terminales");

		return null;
	}

	public Void modificarTerminal(Request request, Response response) {
		Integer idDeTerminalAModificar = Integer.parseInt(request.queryParams("id"));
		Terminal terminalModificada = construirTerminalAPartirDeParametros(request);

		terminalRepository.modificarTerminal(idDeTerminalAModificar, terminalModificada);

		response.redirect("/terminales");

		return null;
	}

	public Void eliminarTerminal(Request request, Response response) {
		terminalRepository.eliminarTerminal(Integer.parseInt(request.queryParams("id")));

		response.redirect("/terminales");

		return null;
	}

	public Terminal construirTerminalAPartirDeParametros(Request request) {
		String nombre = request.queryParams("nombre");
		Double longitud = Double.valueOf(request.queryParams("longitud"));
		Double latitud = Double.valueOf(request.queryParams("latitud"));
		Posicion coordenadas = new Posicion(longitud, latitud);
		Comuna comuna = comunaRepository.obtenerComunaPor(Integer.parseInt(request.queryParams("comuna")));

		return new Terminal(nombre, coordenadas, comuna);
	}
}