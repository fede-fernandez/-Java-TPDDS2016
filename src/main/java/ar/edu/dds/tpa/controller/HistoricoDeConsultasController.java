package ar.edu.dds.tpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.PuntosDeInteresEncontrados;
import ar.edu.dds.tpa.persistencia.Persistible;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistoricoDeConsultasController implements Persistible{
	
	private HistorialDeBusqueda historial;

	public HistoricoDeConsultasController(HistorialDeBusqueda historial) {
		this.historial = historial;
	}	
	
	public ModelAndView listarPoisConsultados(Request request, Response response){
		Map<String,List<Busqueda>> model = new HashMap<String,List<Busqueda>>();
				
		List<Busqueda> busquedas = historial.traerTodasLasBusquedas();
		
		model.put("busquedas", busquedas);
		
		return new ModelAndView(model, "historial/consultas.hbs");
	}
	
	public ModelAndView mostrarPOIsEncontradosDeUnaBusqueda(Request request, Response response){
		Map<String,PuntosDeInteresEncontrados> model = new HashMap<>();
		
		String id = request.params("id");
		Busqueda busqueda = historial.encontrarBusquedaPorId(id);
		
		model.put("poisEncontrados", busqueda.getPuntosDeInteresEncontrados());
		
		return new ModelAndView(model, "historial/poisEncontrados.hbs");
	}
	

	public HistorialDeBusqueda getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialDeBusqueda historial) {
		this.historial = historial;
	}

}
