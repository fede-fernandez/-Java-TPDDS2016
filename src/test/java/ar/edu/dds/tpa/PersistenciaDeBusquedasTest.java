package ar.edu.dds.tpa;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDeBusquedasTest {

	private HistorialDeBusqueda historialDeBusqueda;

	private Busqueda busquedaDePrueba1;
	private Busqueda busquedaDePrueba2;
	private Busqueda busquedaDePrueba3;

	private LocalComercial localComercial;
	private ParadaDeColectivo paradaDeColectivo;
	private Banco banco;
	private List<PuntoDeInteres> resultadosDeBusqueda1;
	private List<PuntoDeInteres> resultadosDeBusqueda2;
	private List<PuntoDeInteres> resultadosDeBusqueda3;

	private List<LocalDate> fechasDeBusqueda;

	private Usuario usuario;

	@Before
	public void inicializar() {

		usuario = new Usuario("Juan", new Posicion(30.0, 15.0), new Comuna(3, "Recoleta"));

		localComercial = new LocalComercial();
		paradaDeColectivo = new ParadaDeColectivo();
		banco = new Banco();
		resultadosDeBusqueda1 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda2 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda3 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda1.add(banco);
		resultadosDeBusqueda1.add(paradaDeColectivo);
		resultadosDeBusqueda2.add(localComercial);
		resultadosDeBusqueda2.add(banco);
		resultadosDeBusqueda3.add(paradaDeColectivo);
		resultadosDeBusqueda3.add(banco);
		resultadosDeBusqueda3.add(localComercial);

		busquedaDePrueba1 = new Busqueda(usuario, "Florida", resultadosDeBusqueda1,
				LocalDate.of(2016, Month.FEBRUARY, 4), 5.0);
		busquedaDePrueba2 = new Busqueda(usuario, "Ahorro", resultadosDeBusqueda2, LocalDate.of(2016, Month.APRIL, 5),
				5.0);
		busquedaDePrueba3 = new Busqueda(usuario, "Subte", resultadosDeBusqueda3, LocalDate.of(2016, Month.AUGUST, 9),
				5.0);

		historialDeBusqueda = new HistorialDeBusqueda();
	}

	@Test
	public void cantidadDeResultadosEnBusquedaPersistida() {
		historialDeBusqueda.almacenar(busquedaDePrueba1);
		Assert.assertEquals(resultadosDeBusqueda1.size(),
				historialDeBusqueda.traerBusquedaPor(busquedaDePrueba1.getId()).getCantidadDeResultados());
	}

	@Test
	public void palabraBuscadaEnBusquedaPersistida() {
		historialDeBusqueda.almacenar(busquedaDePrueba2);
		Assert.assertEquals(busquedaDePrueba2.getTextoBuscado(),
				historialDeBusqueda.traerBusquedaPor(busquedaDePrueba2.getId()).getTextoBuscado());
	}

	@Test
	public void fechaEnBusquedaPersistida() {
		historialDeBusqueda.almacenar(busquedaDePrueba3);
		Assert.assertEquals(busquedaDePrueba3.getFechaDeBusqueda(),
				historialDeBusqueda.traerBusquedaPor(busquedaDePrueba3.getId()).getFechaDeBusqueda());
	}

	@Test
	public void cantidadDeResultadosTotalesEnBusquedasPersistidas() {
		historialDeBusqueda.almacenar(busquedaDePrueba1);
		historialDeBusqueda.almacenar(busquedaDePrueba2);
		historialDeBusqueda.almacenar(busquedaDePrueba3);
		int cantidadDeResultadosLocales = busquedaDePrueba1.getCantidadDeResultados()
				+ busquedaDePrueba2.getCantidadDeResultados() + busquedaDePrueba3.getCantidadDeResultados();

		int cantidadDeResultadosPersistidos = historialDeBusqueda.traerBusquedas().stream()
				.mapToInt(unaBusqueda -> unaBusqueda.getCantidadDeResultados()).sum();

		Assert.assertEquals(cantidadDeResultadosLocales, cantidadDeResultadosPersistidos);
	}
}