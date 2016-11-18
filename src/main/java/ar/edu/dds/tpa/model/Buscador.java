package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.persistencia.Mapa;
import ar.edu.dds.tpa.service.BuscadorExterno;

public class Buscador {

	private Mapa mapaLocal;
	private List<BuscadorExterno> buscadoresExternos;

	private HistorialDeBusqueda historialDeBusqueda;

	public Buscador(Mapa unMapa, HistorialDeBusqueda unHistorial) {
		mapaLocal = unMapa;
		buscadoresExternos = new ArrayList<BuscadorExterno>();
		historialDeBusqueda = unHistorial;
	}

	public void registrarBusqueda(Terminal terminal, String textoBuscado, List<PuntoDeInteres> puntosDeInteresEncontrados,
			LocalDateTime tiempoDeInicio, LocalDateTime tiempoDeFin) {

		double duracionDeBusqueda = ChronoUnit.SECONDS.between(tiempoDeInicio, tiempoDeFin);
		Busqueda busquedaRealizada = new Busqueda(terminal, textoBuscado, puntosDeInteresEncontrados, LocalDate.now(),
				duracionDeBusqueda);

		if (terminal != null) {
			terminal.notificarBusqueda(busquedaRealizada);
		}

		historialDeBusqueda.registrarBusqueda(busquedaRealizada);
	}

	public List<PuntoDeInteres> buscar(String unaFrase, Terminal unaTerminal) {

		LocalDateTime comienzoDeBusqueda = LocalDateTime.now();
		List<PuntoDeInteres> puntosDeInteresEncontrados = new ArrayList<PuntoDeInteres>();

		puntosDeInteresEncontrados.addAll(buscarLocalmente(obtenerPalabrasDeUnaFrase(unaFrase)));
		puntosDeInteresEncontrados.addAll(buscarExternamente(obtenerPalabrasDeUnaFrase(unaFrase)));

		registrarBusqueda(unaTerminal, unaFrase, puntosDeInteresEncontrados, comienzoDeBusqueda, LocalDateTime.now());

		return puntosDeInteresEncontrados;
	}

	public List<String> obtenerPalabrasDeUnaFrase(String unaFrase) {
		return Arrays.asList(unaFrase.split(" "));
	}

	public List<PuntoDeInteres> buscarLocalmente(List<String> palabrasDeBusqueda) {
		List<PuntoDeInteres> puntosDeInteresEncontradosLocalmente = new ArrayList<PuntoDeInteres>();

		puntosDeInteresEncontradosLocalmente.addAll(mapaLocal.obtenerPuntosDeInteres().stream()
				.filter(unPuntoDeInteres -> unPuntoDeInteres.estaActivo() && palabrasDeBusqueda.stream()
						.anyMatch(unaPalabra -> unPuntoDeInteres.contienePalabraClave(unaPalabra)))
				.collect(Collectors.toList()));

		return puntosDeInteresEncontradosLocalmente;
	}

	public List<PuntoDeInteres> buscarExternamente(List<String> palabrasDeBusqueda) {

		List<PuntoDeInteres> puntosDeInteresEncontradosExternamente = new ArrayList<PuntoDeInteres>();

		buscadoresExternos.stream().forEach(unBuscador -> puntosDeInteresEncontradosExternamente
				.addAll(unBuscador.buscarPor(palabrasDeBusqueda)));

		return puntosDeInteresEncontradosExternamente;
	}

	public int cantidadDeResultadosDeUnaBusqueda(List<PuntoDeInteres> resultados) {
		return resultados.size();
	}

	public void agregarBuscadorExterno(BuscadorExterno unBuscadorExterno) {
		buscadoresExternos.add(unBuscadorExterno);
	}

	public HistorialDeBusqueda getHistorialDeBusqueda() {
		return historialDeBusqueda;
	}
}