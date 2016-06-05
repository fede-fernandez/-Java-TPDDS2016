
package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.excepcion.FaltaDePermisosExcepcion;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.observer.BusquedaObserver;

public class Terminal {
	private String nombre;
	private Posicion coordenadas;
	private Mapa mapa;
	private List<BusquedaRealizada> busquedasRealizadas;
	private List<BusquedaObserver> observadoresDeBusqueda;
	private boolean permisoParaGenerarReportes;
	
	public Terminal(String nombre, Posicion coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		busquedasRealizadas = new ArrayList<BusquedaRealizada>();
		observadoresDeBusqueda = new ArrayList<BusquedaObserver>();
		permisoParaGenerarReportes = false;
	}

	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public List<PuntoDeInteres> buscarPorTextoLibre(String unaFrase) {
		LocalDateTime comienzoDeBusqueda = LocalDateTime.now();
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();
		puntosDeInteresEncontrados = mapa.buscarPorTextoLibre(unaFrase);
		LocalDateTime finalizacionDeBusqueda = LocalDateTime.now();

		BusquedaRealizada busquedaRealizada = new BusquedaRealizada(unaFrase,
				cantidadDeResultadosArrojados(puntosDeInteresEncontrados), LocalDate.now(),
				ChronoUnit.SECONDS.between(comienzoDeBusqueda, finalizacionDeBusqueda));

		agregarBusquedaRealizada(busquedaRealizada);

		return puntosDeInteresEncontrados;
	}

	public void agregarBusquedaRealizada(BusquedaRealizada unaBusquedaRealizada) {
		busquedasRealizadas.add(unaBusquedaRealizada);
		observadoresDeBusqueda.forEach(unObservadorDeBusqueda -> unObservadorDeBusqueda.informar(unaBusquedaRealizada));
	}

	public int cantidadDeResultadosArrojados(List<PuntoDeInteres> puntosDeInteresEncontrados) {
		return puntosDeInteresEncontrados.size();
	}

	public void registrarObserverDeBusqueda(BusquedaObserver observadorDeBusqueda) {
		observadoresDeBusqueda.add(observadorDeBusqueda);
	}
	
	public void borrarObserverDeBusqueda(BusquedaObserver observadorDeBusqueda) {
		observadoresDeBusqueda.remove(observadorDeBusqueda);
	}
	
	public int cantidadDeBusquedasPorFecha(LocalDate fecha) throws FaltaDePermisosExcepcion {
		if (permisoParaGenerarReportes) {
			return (int)busquedasRealizadas.stream().filter(unaBusqueda -> fecha.equals(unaBusqueda.getFechaDeBusqueda())).count();			
		} else {
			throw new FaltaDePermisosExcepcion("No contas con los permisos para generar el reporte");
		}
	}
	
	public List<Integer> resultadosParcialesDeUnaBusqueda(String unTexto) throws FaltaDePermisosExcepcion {
		if (permisoParaGenerarReportes) {
			return busquedasRealizadas.stream()
					.filter(unaBusqueda -> unaBusqueda.getTextoBuscado().equals(unTexto))
					.map(unaBusquedaFiltrada -> unaBusquedaFiltrada.getCantidadDeResultados())
					.collect(Collectors.toList());			
		} else {
			throw new FaltaDePermisosExcepcion("No contas con los permisos para generar el reporte");
		}		
	}
	
	public int cantidadTotalDeResultadosDeUnaBusqueda(String unTexto) throws FaltaDePermisosExcepcion {
		if (permisoParaGenerarReportes) {
			return resultadosParcialesDeUnaBusqueda(unTexto).stream()
					.mapToInt(unaCantidad -> unaCantidad.intValue())
					.sum();			
		} else {
			throw new FaltaDePermisosExcepcion("No contas con los permisos para generar el reporte");
		}
		
	}
	
	public void activarReportes() {
		permisoParaGenerarReportes = true;
	}
	
	public void desactivarReportes() {
		permisoParaGenerarReportes = false;
	}
}