package ar.edu.dds.tpa.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.mockito.Mockito;

import ar.edu.dds.tpa.controller.TerminalController;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.repository.TerminalRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalControllerTest {

	private Comuna urquiza;
	private Comuna belgrano;
	private Comuna palermo;

	private Terminal terminalUrquiza;
	private Terminal terminalBelgrano;
	private Terminal terminalCabildo;
	private Terminal terminalJuramento;
	private Terminal terminalPalermo;
	private Terminal terminalPlazaItalia;

	private TerminalRepository terminalRepository;
	private TerminalController terminalController;
	private Request request;
	private Response response;

	@Before
	public void inicializar() {

		urquiza = new Comuna(11, "Urquiza");
		belgrano = new Comuna(12, "Belgrano");
		palermo = new Comuna(13, "Palermo");

		terminalUrquiza = new Terminal("Terminal Urquiza", new Posicion(10.1, 20.1), urquiza);
		terminalBelgrano = new Terminal("Terminal Belgrano", new Posicion(20.2, 30.2), belgrano);
		terminalCabildo = new Terminal("Terminal Cabildo", new Posicion(30.3, 40.3), belgrano);
		terminalJuramento = new Terminal("Terminal Juramento", new Posicion(40.4, 50.4), belgrano);
		terminalPalermo = new Terminal("Terminal Palermo", new Posicion(50.5, 60.5), palermo);
		terminalPlazaItalia = new Terminal("Terminal Plaza Italia", new Posicion(60.6, 70.6), palermo);

		terminalRepository = new TerminalRepository();
		terminalRepository.agregarTerminal(terminalUrquiza);
		terminalRepository.agregarTerminal(terminalBelgrano);
		terminalRepository.agregarTerminal(terminalCabildo);
		terminalRepository.agregarTerminal(terminalJuramento);
		terminalRepository.agregarTerminal(terminalPalermo);
		terminalRepository.agregarTerminal(terminalPlazaItalia);

		terminalController = new TerminalController();

		request = Mockito.mock(Request.class);
		response = Mockito.mock(Response.class);
	}

	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void mostrarTerminalesTest() {
		ModelAndView modelAndView = terminalController.mostrarTerminales(request, response);
		Map<String, List> model = (HashMap<String, List>) modelAndView.getModel();
		Assert.assertEquals(terminalRepository.obtenerTerminales().size(), model.get("terminal").size());
	}
}