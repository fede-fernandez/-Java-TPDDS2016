package ar.edu.dds.tpa;

import java.time.LocalDate;

import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Terminal;

public class GeneracionDeReportesTest {

	private HistorialDeBusqueda historialDeBusqueda;

	private LocalDate cuatroDeFebreroDe2016;
	private LocalDate diezDeEneroDe2016;

	private Busqueda busquedaParadaCinco;
	private Busqueda busquedaLibreria;
	private Busqueda busquedaParadaDos;

	private Terminal terminal;

	/*@Before
	public void inicializar() {

		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);

		terminal = new Terminal();

		busquedaParadaCinco = new Busqueda(terminal, "Parada 5", new ArrayList<PuntoDeInteres>(), cuatroDeFebreroDe2016, 5.0);
		busquedaLibreria = new Busqueda(terminal, "Libreria", new ArrayList<PuntoDeInteres>(), cuatroDeFebreroDe2016, 5.0);
		busquedaParadaDos = new Busqueda(terminal, "Parada 2", new ArrayList<PuntoDeInteres>(), diezDeEneroDe2016, 5.0);

		historialDeBusqueda = new HistorialDeBusqueda();

		historialDeBusqueda.agregar(busquedaParadaCinco);
		historialDeBusqueda.agregar(busquedaLibreria);
		historialDeBusqueda.agregar(busquedaParadaDos);

	}

	@Test
	public void reporteQueTotalizaLasBusquedasPorFecha() {
		assertEquals(2, historialDeBusqueda.cantidadDeBusquedasEnUnaFecha(cuatroDeFebreroDe2016));
	}*/
}