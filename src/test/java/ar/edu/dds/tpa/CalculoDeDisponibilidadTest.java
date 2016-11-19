package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.model.*;

public class CalculoDeDisponibilidadTest {

	private List<DayOfWeek> deLunesAViernes;
	private ParadaDeColectivo colectivo114;
	private LocalComercial unLocalDeDiarios1;
	private CGP cgpDeFlores;
	private Servicio rentas;
	private Servicio multas;
	private Servicio depositos;
	private Banco bancoPatagonia;

	@Before
	public void inicializar() {
		deLunesAViernes = new ArrayList<DayOfWeek>();
		deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

		colectivo114 = new ParadaDeColectivo("114", null, null);

		unLocalDeDiarios1 = new LocalComercial("Diarin", null, null, null);
		unLocalDeDiarios1.agregarHorarioDeAtencionComunEnVariosDias(deLunesAViernes, LocalTime.of(9, 30),
				LocalTime.of(18, 30));

		rentas = new Servicio("Rentas");
		rentas.agregarHorarioDeAtencion(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(16, 45));

		multas = new Servicio("Multas");
		multas.agregarHorarioDeAtencion(DayOfWeek.THURSDAY, LocalTime.of(12, 15), LocalTime.of(19, 20));

		cgpDeFlores = new CGP("CGPFlores", null, null);
		cgpDeFlores.agregarServicio(rentas);
		cgpDeFlores.agregarServicio(multas);

		depositos = new Servicio("Depositos");
		depositos.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(8, 0), LocalTime.of(11, 30));
		depositos.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(14, 15), LocalTime.of(20, 15));

		bancoPatagonia = new Banco("Banco Patagonia", null, null);
		bancoPatagonia.agregarServicio(depositos);
	}

	@Test
	public void testParadaDeColectivoDisponibilidad() {
		assertTrue(colectivo114.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
	}

	@Test
	public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaYHorarioCorrectos() {
		assertTrue(unLocalDeDiarios1.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
	}

	@Test
	public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaIncorrectoYHorarioCorrecto() {
		assertFalse(unLocalDeDiarios1.estaDisponibleEn(LocalDateTime.of(2016, 4, 23, 10, 15, 33)));
	}

	@Test
	public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaCorrectoYHorarioIncorrecto() {
		assertFalse(unLocalDeDiarios1.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 20, 30, 30)));
	}

	@Test
	public void testCGPSinServicioDefinidoDiaYHorarioCorrectos() {
		assertTrue(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 6, 2, 13, 15, 33)));
	}

	@Test
	public void testCGPSinServicioDefinidoDiaIncorrectoYHorarioCorrecto() {
		assertFalse(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 6, 22, 10, 15, 33)));
	}

	@Test
	public void testCGPConServicioDefinidoCorrectoDiaYHorarioCorrectos() {
		assertTrue(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 6, 7, 12, 30, 30), "Rentas"));
	}

	@Test
	public void testCGPConServicioDefinidoIncorrectoDiaYHorarioCorrectos() {
		assertFalse(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30), "Renovaciones"));
	}

	@Test
	public void testBancoEnDiaHabilHorarioCorrecto() {
		assertTrue(bancoPatagonia.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30)));
	}

	@Test
	public void testBancoEnDiaHabilHorarioIncorrecto() {
		assertFalse(bancoPatagonia.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 19, 30, 30)));
	}
}