package ar.edu.dds.tpa.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.builder.ConsultaBusquedaBuilder;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaBusqueda;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaNula;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorCantidadDeResultados;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorRangoDeFechas;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorTerminal;
import ar.edu.dds.tpa.persistencia.repository.TerminalRepository;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusqueda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistoricoDeConsultasController implements Persistible {

	private HistorialDeBusqueda historial;
	private TerminalRepository repositorioDeTerminales = new TerminalRepository();

	public HistoricoDeConsultasController(HistorialDeBusqueda historial) {
		this.historial = historial;
	}

	public ModelAndView mostrarFiltrosDeHistorial(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		
		List<Terminal> terminales = repositorioDeTerminales.obtenerTerminales();		
		
		model.put("terminales", terminales);
		
		return new ModelAndView(model, "Administrador/historial/filtros.hbs");
	}

	public ModelAndView mostrarPOIsEncontradosDeUnaBusqueda(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();

		String id = request.params("id");

		Busqueda busqueda = historial.encontrarBusquedaPorId(id);
		List<PuntoDeInteres> pois = busqueda.getPuntosDeInteresEncontrados().getPuntosDeInteresEncontrados();

		model.put("pois", pois);

		return new ModelAndView(model, "Administrador/historial/poisEncontrados.hbs");

		
	}
	
	public ModelAndView filtrarPOIs(Request request, Response response){
		Map<String, Object> model = new HashMap<>();
		
		String terminal = request.queryParams("terminales");
		String fechaDeInicio = request.queryParams("fechaInicial");
		String fechaDeFin = request.queryParams("fechaFinal");
		String cantidadDeResultados = request.queryParams("cantResultados");
		ConsultaBusqueda consultaPorTerminal = new ConsultaNula();
		ConsultaBusqueda consultaPorFechas = new ConsultaNula();
		ConsultaBusqueda consultaPorCantidadDeResultados = new ConsultaNula();
		List<ConsultaBusqueda> consultas;
		ConsultaBusquedaBuilder builderDeConsultas = new ConsultaBusquedaBuilder();
		Query<Busqueda> consulta;
		List<Busqueda> busquedas;

		if (!terminal.isEmpty()) {
			consultaPorTerminal = new ConsultaPorTerminal(terminal);
		}
		if (!fechaDeInicio.isEmpty() && !fechaDeFin.isEmpty()) {
			LocalDate fechaInicial = LocalDate.parse(fechaDeInicio);
			LocalDate fechaFinal = LocalDate.parse(fechaDeFin);
			consultaPorFechas = new ConsultaPorRangoDeFechas(fechaInicial, fechaFinal);
		}
		if (!cantidadDeResultados.isEmpty()) {
			consultaPorCantidadDeResultados = new ConsultaPorCantidadDeResultados(Integer.parseInt(cantidadDeResultados));
		}
		
		consultas = Arrays.asList(consultaPorTerminal,consultaPorFechas,consultaPorCantidadDeResultados);
		builderDeConsultas.setConsultas(consultas);
		consulta = builderDeConsultas.construirConsulta();
		
		busquedas = historial.ejecutarConsulta(consulta);
		
		model.put("busquedas", busquedas);		
		
		return new ModelAndView(model, "Administrador/historial/consultas.hbs");
		
	}

	public void setHistorial(HistorialDeBusqueda historial) {
		this.historial = historial;
	}
}