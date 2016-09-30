package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.*;

import java.time.*;
import java.util.Arrays;

public class PersistenciaDePuntosDeInteresTest {

	private Mapa mapa;
	private Usuario terminalFlores;

	@Before
	public void inicializar() {
		mapa = new Mapa();
		terminalFlores = new Usuario("Terminal de Flores", new Posicion(5.000003, 7.000005), null);
	}

	@Test
	public void persistenciaDeParadaDeColectivo() {
		ParadaDeColectivo paradaDel7 = new ParadaDeColectivo("Linea 7 Estacion Once", new Posicion(3.332132, 11.49385));
		mapa.almacenar(paradaDel7);
		Assert.assertEquals(paradaDel7, mapa.traerPuntoDeInteresPor(paradaDel7.getId()));
	}

	@Test
	public void elUsuarioEstaCercaDeLaParadaDeColectivosPersistida() {
		ParadaDeColectivo paradaDel114 = new ParadaDeColectivo("Linea 114 Estacion Escalada", new Posicion(5.000005, 7.000007));
		mapa.almacenar(paradaDel114);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(paradaDel114.getId()).estaCercaDe(terminalFlores.getCoordenadas()));
	}

	@Test
	public void laParadaDeColectivosPersistidaTienePalabraClave() {
		ParadaDeColectivo paradaDel101 = new ParadaDeColectivo("Linea 101 Estacion Retiro", new Posicion(100.22113, 14.30985));
		mapa.almacenar(paradaDel101);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(paradaDel101.getId()).contienePalabraClave("RETIRO"));
	}

	@Test
	public void persistenciaDeLocalComercial() {
		Rubro libreria = new Rubro("Libreria", 22.3);
		LocalComercial localLibreria = new LocalComercial("Lapiceras Magicas", new Posicion(33.21231, 15.49494), libreria);
		mapa.almacenar(localLibreria);
		Assert.assertEquals(localLibreria, mapa.traerPuntoDeInteresPor(localLibreria.getId()));
	}

	@Test
	public void persistenciaDeLocalComercialTieneRubro() {
		Rubro articulosDeLimpieza = new Rubro("Articulos de limpieza", 3.1);
		LocalComercial localDeArticulosDeLimpieza = new LocalComercial("LimpiaMall", new Posicion(7.77867, 232.23321), articulosDeLimpieza);
		mapa.almacenar(localDeArticulosDeLimpieza);
		Assert.assertEquals(articulosDeLimpieza.getNombre(),
				((LocalComercial) mapa.traerPuntoDeInteresPor(localDeArticulosDeLimpieza.getId())).getRubro().getNombre());
	}

	@Test
	public void elLocalComercialPersistidoContieneSuRubroComoPalabraClave() {
		Rubro almacen = new Rubro("Almacen", 1.6);
		LocalComercial almacenDonNato = new LocalComercial("Don Nato", new Posicion(372.87462, 9.65653), almacen);
		mapa.almacenar(almacenDonNato);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(almacenDonNato.getId()).contienePalabraClave("almacen"));
	}

	@Test
	public void elUsuarioEstaCercaDelLocalComercialPersistido() {
		Rubro carpinteria = new Rubro("Carpinteria", 211.50);
		LocalComercial carpinteriaPajaroLoco = new LocalComercial("El Pajaro Loco", new Posicion(5.000004, 7.000002), carpinteria);
		mapa.almacenar(carpinteriaPajaroLoco);
		Assert.assertTrue(
				mapa.traerPuntoDeInteresPor(carpinteriaPajaroLoco.getId()).estaCercaDe(terminalFlores.getCoordenadas()));
	}

	@Test
	public void persistenciaDeVariosPuntosDeInteresContieneLaParadaDeColectivoYElPuestoDeDiarios() {
		ParadaDeColectivo paradaDel46 = new ParadaDeColectivo("Linea 46 Estacion Mozart", new Posicion(53.97583, 12.21985));
		
		Rubro kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		LocalComercial localDeDiarios = new LocalComercial("Diarin", new Posicion(734.1523, 751.2312), kioscoDeDiarios);
		
		Banco bancoPatagonia = new Banco("Banco Patagonia Sucursal Villa Fiorito", new Posicion(73.002005, 148.42205));
		Servicio extracciones = new Servicio("extracciones");
		extracciones.agregarHorarioDeAtencion(DayOfWeek.WEDNESDAY, LocalTime.of(9, 50), LocalTime.of(23, 45));
		bancoPatagonia.agregarServicio(extracciones);
		
		mapa.almacenar(paradaDel46);
		mapa.almacenar(localDeDiarios);
		mapa.almacenar(bancoPatagonia);
		
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream()
				.anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("MOZART")));
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream()
				.anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("DIARIN")));
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream()
				.anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("patagonia")));
	}

	@Test
	public void bancoPersistidoAtiendeEnHorarioBancario() {
		Banco bancoCiudad = new Banco("Banco Ciudad Sucursal Hospital Piñero", new Posicion(43.13620625, 78.5719215));
		mapa.almacenar(bancoCiudad);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(bancoCiudad.getId())
				.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30)));
	}
	
	@Test
	public void bancoPersistidoTieneServicio() {
		Banco bancoSantander = new Banco("Banco Santander Rio Sucursal La Plata", new Posicion(1.12, 1.00295));
		Servicio depositos = new Servicio("depositos");
		depositos.agregarHorarioDeAtencion(DayOfWeek.MONDAY, LocalTime.of(19, 20), LocalTime.of(22, 30));
		bancoSantander.agregarServicio(depositos);
		mapa.almacenar(bancoSantander);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(bancoSantander.getId()).contienePalabraClave("DePoSiToS"));
	}

	@Test
	public void cgpPersistidoTieneServicioDeMultas() {
		CGP cgpParqueChacabuco = new CGP("CGP Numero 14 Parque Chacabuco", new Posicion(912.152023, 62.0008));
		Servicio renovacionDNI = new Servicio("Renovacion de DNI");
		renovacionDNI.agregarHorarioDeAtencion(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
				LocalTime.of(9, 15), LocalTime.of(14, 45));
		cgpParqueChacabuco.agregarServicio(renovacionDNI);
		mapa.almacenar(cgpParqueChacabuco);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(cgpParqueChacabuco.getId()).contienePalabraClave("renovacion"));
	}

	@Test
	public void cgpPersistidoAtiendeEnHorarioDelServicioMultas() {
		CGP cgpMataderos = new CGP("CGP Numero 2 Mataderos", new Posicion(60.33214, 67.3434));
		Servicio multas = new Servicio("multas");
		multas.agregarHorarioDeAtencion(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY),
				LocalTime.of(8, 0), LocalTime.of(16, 30));
		cgpMataderos.agregarServicio(multas);
		mapa.almacenar(cgpMataderos);
		Assert.assertTrue(((PuntoDeInteresConServicios) mapa.traerPuntoDeInteresPor(cgpMataderos.getId()))
				.estaDisponibleEn(LocalDateTime.of(2016, 9, 28, 12, 30, 30), "multas"));
	}
}