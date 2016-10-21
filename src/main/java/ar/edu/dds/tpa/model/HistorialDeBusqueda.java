package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class HistorialDeBusqueda {

	@Id
	private ObjectId id;
	
	List<Busqueda> busquedasRealizadas;

	public HistorialDeBusqueda() {
		busquedasRealizadas = new ArrayList<Busqueda>();
	}
	
	public HistorialDeBusqueda(List<Busqueda> busquedasRealizadas){
		this.busquedasRealizadas = busquedasRealizadas;
	}

	public void agregar(Busqueda unaBusqueda) {
		busquedasRealizadas.add(unaBusqueda);
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
		return busquedasRealizadas.stream().mapToInt(unaBusqueda -> unaBusqueda.getCantidadDeResultados()).sum();
	}

	public int cantidadDeBusquedasEnUnaFecha(LocalDate unaFecha) {
		return (int) busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getFechaDeBusqueda().equals(unaFecha)).count();
	}

	public List<Integer> resultadosParcialesPorBusqueda(String textoBuscado) {
		return busquedasRealizadas.parallelStream()
				.filter(unaBusqueda -> unaBusqueda.getTextoBuscado().equals(textoBuscado))
				.map(unaBusquedaFiltrada -> (Integer) unaBusquedaFiltrada.getCantidadDeResultados())
				.collect(Collectors.toList());
	}

	public List<Busqueda> getBusquedasRealizadas() {
		return busquedasRealizadas;
	}

	public List<String> nombresDeUsuarios() {
		return busquedasRealizadas.stream().map(unaBusqueda -> unaBusqueda.getUsuario().getNombre()).distinct()
				.collect(Collectors.toList());
	}

	public int cantidadDeResultadosTotalesDeUnUsuario(String unNombreDeUsuario) {
		return busquedasRealizadas.stream()
				.filter(unaBusqueda -> unaBusqueda.getUsuario().getNombre().equals(unNombreDeUsuario))
				.mapToInt(unaBusquedaFiltrada -> unaBusquedaFiltrada.getCantidadDeResultados()).sum();
	}
	
	public int cantidadDeBusquedasRealizadas(){
		return busquedasRealizadas.size();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setBusquedasRealizadas(List<Busqueda> busquedasRealizadas) {
		this.busquedasRealizadas = busquedasRealizadas;
	}
}