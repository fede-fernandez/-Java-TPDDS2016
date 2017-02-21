package ar.edu.dds.tpa.persistencia.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Repositorio;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusquedaEnMemoria;

public class MapaEnBaseDeDatosTest {

	private MapaEnBaseDeDatos mapa;
	private ParadaDeColectivo paradaDel46;
	private Rubro kioscoDeDiarios;
	private LocalComercial localDeDiarios;
	private List<DayOfWeek> deLunesAViernes;
	private Banco bancoPatagonia;
	private Servicio extracciones;
	private Buscador buscador;
	private Terminal terminalChacabuco;
	private Comuna comuna12;

	@Before
	public void inicializar() {
		mapa = new MapaEnBaseDeDatos();

		paradaDel46 = new ParadaDeColectivo("Linea 46 Estacion Mozart", new Posicion(53.97583, 12.21985),
				"calle falsa 123");
		paradaDel46.agregarPalabraClave("colectivo");

		kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		localDeDiarios = new LocalComercial("Diarin", new Posicion(734.1523, 751.2312), kioscoDeDiarios,
				"calle kiosco 123");

		deLunesAViernes = new ArrayList<DayOfWeek>();
		deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
		localDeDiarios.agregarHorarioDeAtencionComunEnVariosDias(deLunesAViernes, LocalTime.of(9, 30),
				LocalTime.of(18, 30));

		bancoPatagonia = new Banco("Banco Patagonia Sucursal Villa Fiorito", new Posicion(73.002005, 148.42205),
				"calle banco 123");
		extracciones = new Servicio("extracciones");
		extracciones.agregarHorarioDeAtencion(DayOfWeek.WEDNESDAY, LocalTime.of(9, 50), LocalTime.of(23, 45));
		bancoPatagonia.agregarServicio(extracciones);

		buscador = new Buscador(mapa, new HistorialDeBusquedaEnMemoria());
		
		terminalChacabuco = new Terminal("Terminal Chacabuco", new Posicion(5.0, 5.0), comuna12);
	}

	@Test
	public void buscarPorNombreTest() {
		mapa.agregar(paradaDel46);
		Assert.assertEquals(buscador.buscar("mozart", terminalChacabuco).get(0), paradaDel46);
	}
	
	@Test
	public void buscarPorRubroTest() {
		mapa.agregar(localDeDiarios);
		Assert.assertEquals(buscador.buscar("kiosco de diarios", terminalChacabuco).get(0), localDeDiarios);
	}
	
	@Test
	public void buscarPorServicioTest() {
		mapa.agregar(bancoPatagonia);
		Assert.assertEquals(buscador.buscar("extracciones", terminalChacabuco).get(0), bancoPatagonia);
	}
	
	@AfterClass
	public static void guardarYCerrarSesion() {
		Repositorio.obtenerRepositorioANivelThread().cerrarSesion();
	}
}