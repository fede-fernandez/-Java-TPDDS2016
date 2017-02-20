package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.PuntosDeInteresEncontrados;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusquedaEnMemoria;

public class GeneracionDeReportesTest {

	private HistorialDeBusquedaEnMemoria historialDeBusqueda;

	private LocalDate cuatroDeFebreroDe2016;
	private LocalDate diezDeEneroDe2016;

	private Busqueda busquedaParadaCinco;
	private Busqueda busquedaLibreria;
	private Busqueda busquedaParadaDos;

	private Terminal terminal;

	@Before
	public void inicializar() {

		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);

		PuntosDeInteresEncontrados puntosDeInteresEncontrados = new PuntosDeInteresEncontrados();

		terminal = new Terminal();
		busquedaParadaCinco = new Busqueda(terminal, "Parada 5", puntosDeInteresEncontrados, cuatroDeFebreroDe2016,
				5.0);
		busquedaParadaCinco = new Busqueda(terminal, "Parada 5", puntosDeInteresEncontrados, cuatroDeFebreroDe2016,
				5.0);
		busquedaLibreria = new Busqueda(terminal, "Libreria", puntosDeInteresEncontrados, cuatroDeFebreroDe2016, 5.0);
		busquedaParadaDos = new Busqueda(terminal, "Parada 2", puntosDeInteresEncontrados, diezDeEneroDe2016, 5.0);

		historialDeBusqueda = new HistorialDeBusquedaEnMemoria();

		historialDeBusqueda.registrarBusqueda(busquedaParadaCinco);
		historialDeBusqueda.registrarBusqueda(busquedaLibreria);
		historialDeBusqueda.registrarBusqueda(busquedaParadaDos);

	}

	@Test
	public void reporteQueTotalizaLasBusquedasPorFecha() {
		assertEquals(2, historialDeBusqueda.cantidadDeBusquedasEnUnaFecha(cuatroDeFebreroDe2016));
	}
}