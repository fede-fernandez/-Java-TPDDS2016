package ar.edu.dds.tpa.historial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.model.Busqueda;


public class HistorialDeBusquedaEnMemoria implements HistorialDeBusqueda{

	List<Busqueda> busquedasRealizadas;

	public HistorialDeBusquedaEnMemoria() {
		busquedasRealizadas = new ArrayList<Busqueda>();
	}
	
	public HistorialDeBusquedaEnMemoria(List<Busqueda> busquedasRealizadas){
		this.busquedasRealizadas = busquedasRealizadas;
	}

	
	public List<LocalDate> fechasDeBusquedas() {
		return busquedasRealizadas.stream().map(unaBusqueda -> unaBusqueda.getFechaDeBusqueda()).distinct()
				.collect(Collectors.toList());
	}

	public List<String> textosBuscados() {
		return busquedasRealizadas.stream().map(unaBusqueda -> unaBusqueda.getTextoBuscado())
				.collect(Collectors.toList());
	}

	public int cantidadDeResultadosTotales() {
		return busquedasRealizadas.stream().mapToInt(unaBusqueda -> unaBusqueda.getPuntosDeInteresEncontrados().getCantidadDeResultados()).sum();
	}

	public int cantidadDeBusquedasEnUnaFecha(LocalDate unaFecha) {
		return (int) busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getFechaDeBusqueda().equals(unaFecha)).count();
	}

	public List<Integer> resultadosParcialesPorBusqueda(String textoBuscado) {
		return busquedasRealizadas.parallelStream()
				.filter(unaBusqueda -> unaBusqueda.getTextoBuscado().equals(textoBuscado))
				.map(unaBusquedaFiltrada -> (Integer) unaBusquedaFiltrada.getPuntosDeInteresEncontrados().getCantidadDeResultados())
				.collect(Collectors.toList());
	}


	public List<String> nombresDeUsuarios() {
		return busquedasRealizadas.stream().map(unaBusqueda -> unaBusqueda.getTerminal().getNombre()).distinct()
				.collect(Collectors.toList());
	}

	public int cantidadDeResultadosTotalesDeUnUsuario(String unNombreDeUsuario) {
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getTerminal().getNombre().equals(unNombreDeUsuario))
				.mapToInt(unaBusquedaFiltrada -> unaBusquedaFiltrada.getPuntosDeInteresEncontrados().getCantidadDeResultados()).sum();
	}
	

	public void registrarBusqueda(Busqueda unaBusqueda) {
		busquedasRealizadas.add(unaBusqueda);
	}
	
	public void eliminarBusqueda(Busqueda unaBusqueda){
		busquedasRealizadas.remove(unaBusqueda);
	}
	
	public int cantidadDeBusquedasRealizadas(){
		return busquedasRealizadas.size();
	}

	public void setBusquedasRealizadas(List<Busqueda> busquedasRealizadas) {
		this.busquedasRealizadas = busquedasRealizadas;
	}

	public List<Busqueda> traerTodasLasBusquedas() {
		return busquedasRealizadas;
	}

	
	public List<Busqueda> encontrarTodasLasBusquedasPorTextoBuscado(String unTexto) {
		return busquedasRealizadas.stream()
		.filter(unaBusqueda -> unaBusqueda.getTextoBuscado().equals(unTexto))
		.collect(Collectors.toList());
		
	}
	
	public List<Busqueda> encontrarLasBusquedasEntreDosFechas(LocalDate fechaDeInicio, LocalDate fechaDeFin) {
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> estaEnUnRangoDeFechas(unaBusqueda.getFechaDeBusqueda(), fechaDeInicio, fechaDeFin))
				.collect(Collectors.toList());
	}
	
	public List<Busqueda> encontrarLasBusquedasEntreDosFechasYPorTextoBuscado(String textoBuscado,
			LocalDate fechaDeInicio, LocalDate fechaDeFin) {
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> estaEnUnRangoDeFechas(unaBusqueda.getFechaDeBusqueda(), fechaDeInicio, fechaDeFin)
						&& unaBusqueda.getTextoBuscado().equals(textoBuscado))
				.collect(Collectors.toList());
	}
	
	public List<Busqueda> encontrarLasBusquedasQueSeRealizaronEnUnaFecha(LocalDate unaFecha) {
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getFechaDeBusqueda().equals(unaFecha))
				.collect(Collectors.toList());
	}
	
	public List<Busqueda> encontrarLasBusquedasDeUnaTerminal(String nombreDeTerminal){
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getTerminal().getNombre().equals(nombreDeTerminal))
				.collect(Collectors.toList());
	}
	
	public List<Busqueda> encontrarLasBusquedasPorCantidadDeResultados(int cantidadDeResultados){
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getPuntosDeInteresEncontrados().getCantidadDeResultados() == cantidadDeResultados)
				.collect(Collectors.toList());
	}
	
	private boolean estaEnUnRangoDeFechas(LocalDate fecha, LocalDate fechaDeInicio, LocalDate fechaDeFin){
		return !fecha.isBefore(fechaDeInicio) && !fecha.isAfter(fechaDeFin);
	}
}