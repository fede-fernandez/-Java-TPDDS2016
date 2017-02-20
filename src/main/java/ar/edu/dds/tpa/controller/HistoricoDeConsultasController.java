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
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorTexto;
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

	public ModelAndView listarPoisConsultados(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();

		List<Busqueda> busquedas = historial.traerTodasLasBusquedas();
		List<Terminal> terminales = repositorioDeTerminales.obtenerTerminales();
//		Usuario usuario = request.session().attribute("usuario");
//		model.put("nombre", usuario.getUsuario());
		
		model.put("terminales", terminales);
		model.put("busquedas", busquedas);

		return new ModelAndView(model, "Administrador/historial/consultas.hbs");
	}

	public ModelAndView mostrarPOIsEncontradosDeUnaBusqueda(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();

		String id = request.params("id");
		List<Busqueda> busquedas = historial.traerTodasLasBusquedas();
		Busqueda busqueda = historial.encontrarBusquedaPorId(id);
		List<PuntoDeInteres> pois = busqueda.getPuntosDeInteresEncontrados().getPuntosDeInteresEncontrados();

		model.put("pois", pois);

		return new ModelAndView(model, "Administrador/historial/poisEncontrados.hbs");
//		return new ModelAndView(model, "Terminal/busquedaVisualizacionPOIs/mostrarPOIs.hbs");
		
	}
	
	public ModelAndView filtrarPOISPorTerminal(Request request, Response response){
		Map<String, Object> model = new HashMap<>();
		
		String nombreDeTerminal = request.queryParams("terminal");
		ConsultaBusqueda consultaPorTerminal = new ConsultaPorTerminal(nombreDeTerminal);
		ConsultaBusquedaBuilder builderDeConsultas = new ConsultaBusquedaBuilder();
		
		builderDeConsultas.setConsultas(Arrays.asList(consultaPorTerminal));
		
		List<Busqueda> busquedas = historial.ejecutarConsulta(builderDeConsultas.construirConsulta(datastoreMock.getDatastore()));
		List<Terminal> terminales = repositorioDeTerminales.obtenerTerminales();

		
		model.put("terminales", terminales);
		model.put("busquedas", busquedas);
		
		return new ModelAndView(model, "Administrador/historial/consultas.hbs");
	}
	
	public ModelAndView filtrarPOIs(Request request, Response response){
		Map<String, Object> model = new HashMap<>();
		
		//String textoBuscado = request.queryParams("textoBuscado");
		String fechaDeInicio = request.queryParams("fechaInicial");
		String fechaDeFin = request.queryParams("fechaFinal");
		String cantidadDeResultados = request.queryParams("cantResultados");
		ConsultaBusqueda consultaPorTexto = new ConsultaNula();
		ConsultaBusqueda consultaPorFechas = new ConsultaNula();
		ConsultaBusqueda consultaPorCantidadDeResultados = new ConsultaNula();
		List<ConsultaBusqueda> consultas;
		ConsultaBusquedaBuilder builderDeConsultas = new ConsultaBusquedaBuilder();
		Query<Busqueda> consulta;
		List<Busqueda> busquedas;
//		
//		if (textoBuscado != null) {
//			consultaPorTexto = new ConsultaPorTexto(textoBuscado);
//		}
		if (!fechaDeInicio.isEmpty() && !fechaDeFin.isEmpty()) {
			LocalDate fechaInicial = LocalDate.parse(fechaDeInicio);
			LocalDate fechaFinal = LocalDate.parse(fechaDeFin);
			consultaPorFechas = new ConsultaPorRangoDeFechas(fechaInicial, fechaFinal);
		}
		if (!cantidadDeResultados.isEmpty()) {
			consultaPorCantidadDeResultados = new ConsultaPorCantidadDeResultados(Integer.parseInt(cantidadDeResultados));
		}
		
		consultas = Arrays.asList(consultaPorTexto,consultaPorFechas,consultaPorCantidadDeResultados);
		builderDeConsultas.setConsultas(consultas);
		consulta = builderDeConsultas.construirConsulta(datastoreMock.getDatastore());
		
		busquedas = historial.ejecutarConsulta(consulta);
		
		model.put("busquedas", busquedas);		
		
		return new ModelAndView(model, "Administrador/historial/consultas.hbs");
		
	}
	
	private void recuperarParametrosDeFiltros(Request request){
		
	}

	public HistorialDeBusqueda getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialDeBusqueda historial) {
		this.historial = historial;
	}
}