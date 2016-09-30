package ar.edu.dds.tpa.persistencia;

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
	private Comuna congreso;
	private Comuna belgrano;

	@Before
	public void inicializar() {
		historialDeBusqueda = new HistorialDeBusqueda();
		congreso = new Comuna(32, "Congreso");
		belgrano = new Comuna(2, "Belgrano");
	}

	@Test
	public void cantidadDeResultadosEnBusquedaPersistida() {
		Usuario terminalCongreso = new Usuario("Terminal Estacion Congreso", new Posicion(22.2210, 15.51112), congreso);

		Rubro pizzeria = new Rubro("Pizzeria", 12.555);
		LocalComercial pizzeriaUggis = new LocalComercial("Pizzeria Ugis", new Posicion(25.93493, 26.243982), pizzeria);
		LocalComercial pizzeriaFortin = new LocalComercial("Pizzeria El Fortin", new Posicion(70.3883, 28.28963),
				pizzeria);

		List<PuntoDeInteres> resultadosDeBusqueda1 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda1.add(pizzeriaUggis);
		resultadosDeBusqueda1.add(pizzeriaFortin);

		Busqueda busquedaDePrueba1 = new Busqueda(terminalCongreso, "Pizzeria", resultadosDeBusqueda1,
				LocalDate.of(2016, Month.FEBRUARY, 4), 5.0);

		historialDeBusqueda.almacenar(busquedaDePrueba1);
		Assert.assertEquals(resultadosDeBusqueda1.size(),
				historialDeBusqueda.traerBusquedaPor(busquedaDePrueba1.getId()).getCantidadDeResultados());
	}

	@Test
	public void palabraBuscadaEnBusquedaPersistida() {
		Usuario terminalParqueCongreso = new Usuario("Terminal Parque Congreso", new Posicion(24.22350, 16.59322),
				congreso);

		ParadaDeColectivo paradaDe141PlazaItalia = new ParadaDeColectivo("Parada Linea 141 Plaza Italia",
				new Posicion(273.28458, 221.3736));
		ParadaDeColectivo paradaDe141Acoyte = new ParadaDeColectivo("Parada Linea 141 Acoyte",
				new Posicion(32.25218, 111.3452));
		ParadaDeColectivo paradaDe141Nazca = new ParadaDeColectivo("Parada Linea 141 Nazca",
				new Posicion(18.181818, 19.191919));

		List<PuntoDeInteres> resultadosDeBusqueda2 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda2.add(paradaDe141PlazaItalia);
		resultadosDeBusqueda2.add(paradaDe141Acoyte);
		resultadosDeBusqueda2.add(paradaDe141Nazca);

		Busqueda busquedaDePrueba2 = new Busqueda(terminalParqueCongreso, "141", resultadosDeBusqueda2,
				LocalDate.of(2016, Month.AUGUST, 5), 3.5);

		historialDeBusqueda.almacenar(busquedaDePrueba2);
		Assert.assertEquals(busquedaDePrueba2.getTextoBuscado(),
				historialDeBusqueda.traerBusquedaPor(busquedaDePrueba2.getId()).getTextoBuscado());
	}

	@Test
	public void fechaEnBusquedaPersistida() {
		Usuario terminalBelgrano = new Usuario("Terminal Belgrano", new Posicion(39.2828, 102.3388), belgrano);

		Banco bancoPiano = new Banco("Banco Piano Sucursal Almagro", new Posicion(874.28282, 323.34334));

		List<PuntoDeInteres> resultadosDeBusqueda3 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda3.add(bancoPiano);

		Busqueda busquedaDePrueba3 = new Busqueda(terminalBelgrano, "Banco", resultadosDeBusqueda3,
				LocalDate.of(2016, Month.SEPTEMBER, 30), 10.0);

		historialDeBusqueda.almacenar(busquedaDePrueba3);
		Assert.assertEquals(busquedaDePrueba3.getFechaDeBusqueda(),
				historialDeBusqueda.traerBusquedaPor(busquedaDePrueba3.getId()).getFechaDeBusqueda());
	}

	@Test
	public void cantidadDeResultadosTotalesEnBusquedasPersistidas() {
		Usuario terminalBelgranoShopping = new Usuario("Terminal Belgrano Shopping", new Posicion(24.22350, 16.59322),
				belgrano);
		Usuario terminalCabildo = new Usuario("Terminal Cabildo", new Posicion(873.18, 452.172), belgrano);
		Usuario terminalJuramento = new Usuario("Terminal Juramento", new Posicion(348.128, 256.512), belgrano);

		Rubro panaderia = new Rubro("panaderia", 233.0);
		LocalComercial lasMedialunasDelAbuelo = new LocalComercial("Las Medialunas del Abuelo",
				new Posicion(11.11125, 78.210801), panaderia);
		LocalComercial panPanPan = new LocalComercial("PanPanPan", new Posicion(91.13905, 57.128765), panaderia);

		ParadaDeColectivo paradaDe132Varela = new ParadaDeColectivo("Parada Linea 132 Varela",
				new Posicion(99.099, 117.3773));
		ParadaDeColectivo paradaDe132Carabobo = new ParadaDeColectivo("Parada Linea 132 Carabobo",
				new Posicion(30.30312, 17.1789));

		Banco bancoLibertadLanus = new Banco("Banco Libertad Sucursal Lanus", new Posicion(4.79302, 712.48284));
		Banco bancoLibertadVillaDelParque = new Banco("Banco Libertad Sucursal Villa del Parque",
				new Posicion(9000.1123, 83.275));

		List<PuntoDeInteres> resultadosDeBusqueda4 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda4.add(lasMedialunasDelAbuelo);
		resultadosDeBusqueda4.add(panPanPan);

		Busqueda busquedaDePrueba4 = new Busqueda(terminalBelgranoShopping, "Panaderia", resultadosDeBusqueda4,
				LocalDate.of(2016, Month.DECEMBER, 10), 900.3);

		List<PuntoDeInteres> resultadosDeBusqueda5 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda5.add(paradaDe132Varela);
		resultadosDeBusqueda5.add(paradaDe132Carabobo);

		Busqueda busquedaDePrueba5 = new Busqueda(terminalCabildo, "132", resultadosDeBusqueda4,
				LocalDate.of(2015, Month.JANUARY, 11), 30.7);

		List<PuntoDeInteres> resultadosDeBusqueda6 = new ArrayList<PuntoDeInteres>();
		resultadosDeBusqueda6.add(bancoLibertadLanus);
		resultadosDeBusqueda6.add(bancoLibertadVillaDelParque);

		Busqueda busquedaDePrueba6 = new Busqueda(terminalJuramento, "BANCO LIBERTAD", resultadosDeBusqueda4,
				LocalDate.of(2015, Month.MAY, 2), 230.4);

		historialDeBusqueda.almacenar(busquedaDePrueba4);
		historialDeBusqueda.almacenar(busquedaDePrueba5);
		historialDeBusqueda.almacenar(busquedaDePrueba6);

		int cantidadDeResultadosLocales = busquedaDePrueba4.getCantidadDeResultados()
				+ busquedaDePrueba5.getCantidadDeResultados() + busquedaDePrueba6.getCantidadDeResultados();

		int cantidadDeResultadosPersistidos = historialDeBusqueda.traerBusquedas().stream()
				.mapToInt(unaBusqueda -> unaBusqueda.getCantidadDeResultados()).sum();

		Assert.assertEquals(cantidadDeResultadosLocales, cantidadDeResultadosPersistidos);
	}
}