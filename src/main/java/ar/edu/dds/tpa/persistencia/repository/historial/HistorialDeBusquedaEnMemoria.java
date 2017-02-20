package ar.edu.dds.tpa.persistencia.repository.historial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

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

	
		public Busqueda encontrarBusquedaPorId(String id) {
		return null;
	}

	
	public Busqueda encontrarBusquedaPorId(ObjectId id) {
		return null;
	}

	@Override
	public List<Busqueda> ejecutarConsulta(Query<Busqueda> consulta) {
		// TODO Auto-generated method stub
		return null;
	}
}