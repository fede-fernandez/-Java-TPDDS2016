package ar.edu.dds.tpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.*;
import ar.edu.dds.tpa.persistencia.repository.Mapa;
import ar.edu.dds.tpa.persistencia.repository.MapaEnMemoria;
import ar.edu.dds.tpa.procesos.DarDeBajaPuntosDeInteres;
import ar.edu.dds.tpa.service.BajaDePuntoDeInteresServiceReal;

public class DarDeBajaPuntosDeInteresTest {

	private Banco unBanco;
	private Rubro kioscoDeDiario;
	private LocalComercial unLocal;
	private ParadaDeColectivo unaParadaDeColectivo;
	private Mapa mapa;
	private DarDeBajaPuntosDeInteres procesoDarDeBajaPuntosDeInteres;
	private BajaDePuntoDeInteresServiceReal servicioDeBajaDePuntosDeInteres;

	@Before
	public void inicializar() {

		unBanco = new Banco("Banco Ciudad", new Posicion(10.0, 10.0), "Rivadavia 4934");
		unBanco.setId(122);

		kioscoDeDiario = new Rubro("Kiosco De Diarios", 2.0);
		unLocal = new LocalComercial("El matutino", new Posicion(2.0, 2.0), kioscoDeDiario, "Directorio 2392");
		unLocal.setId(123);

		unaParadaDeColectivo = new ParadaDeColectivo("Parada de colectivo 133", new Posicion(4.4, 5.5), "Tandil 834");
		unaParadaDeColectivo.setId(124);

		mapa = new MapaEnMemoria();
		mapa.agregar(unLocal);
		mapa.agregar(unBanco);

		procesoDarDeBajaPuntosDeInteres = new DarDeBajaPuntosDeInteres();
		procesoDarDeBajaPuntosDeInteres.setMapa(mapa);
		servicioDeBajaDePuntosDeInteres = new BajaDePuntoDeInteresServiceReal();
		procesoDarDeBajaPuntosDeInteres.setServicioDeBajaDePuntosDeInteres(servicioDeBajaDePuntosDeInteres);
		procesoDarDeBajaPuntosDeInteres.ejecutar();
	}

	@Test
	public void seDioDeBajaElPuntoDeInteresConId122() {
		Assert.assertFalse(unBanco.estaActivo());
	}

	@Test
	public void seDioDeBajaElPuntoDeInteresConId123() {
		Assert.assertFalse(unLocal.estaActivo());
	}

	@Test
	public void noSeDioDeBajaElPuntoDeInteresConId123PorqueNoLoDaElServicio() {
		Assert.assertTrue(unaParadaDeColectivo.estaActivo());
	}

	@Test
	public void seLeAsignoLaFechaDeBajaAlPuntoDadoDeBajaConId122() {
		Assert.assertEquals(unBanco.getFechaDeBaja(),
				LocalDateTime.parse("2016-06-22T02:12:58.128Z", DateTimeFormatter.ISO_DATE_TIME));
	}

	@Test
	public void seLeAsignoLaFechaDeBajaAlPuntoDadoDeBajaConId123() {
		Assert.assertEquals(unBanco.getFechaDeBaja(),
				LocalDateTime.parse("2016-06-22T02:12:58.128Z", DateTimeFormatter.ISO_DATE_TIME));
	}

	@Test
	public void noSeLeAsignoLaFechaDeBajaAlPuntoQueNoLoDaElServicio() {
		Assert.assertNull(unaParadaDeColectivo.getFechaDeBaja());
	}
}