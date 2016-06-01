package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.model.*;
import ar.edu.dds.tpa.model.HorarioDeAtencion.Dia;

public class CalculoDeDisponibilidadTest {
	List<Dia> deLunesAViernes;
	ParadaDeColectivo colectivo114;
	LocalComercial unLocalDeDiarios1;
	CGP cgpDeFlores;
	Servicio rentas;
	Servicio multas;
	Banco bancoPatagonia;

	@Before
	public void inicializar() {
		deLunesAViernes = new ArrayList<Dia>();
		deLunesAViernes.addAll(Arrays.asList(Dia.LUNES, Dia.MARTES, Dia.MIERCOLES, Dia.JUEVES, Dia.VIERNES));

		colectivo114 = new ParadaDeColectivo("114", null);

		unLocalDeDiarios1 = new LocalComercial("Diarin", null, null);
		unLocalDeDiarios1.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(9, 30), LocalTime.of(18, 30));

		rentas = new Servicio("rentas");
		rentas.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(3, 30), LocalTime.of(23, 30));

		multas = new Servicio("multas");
		multas.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(3, 30), LocalTime.of(23, 45));

		cgpDeFlores = new CGP("CGPFlores", null, null);
		cgpDeFlores.agregarServicio(rentas);
		cgpDeFlores.agregarServicio(multas);

		bancoPatagonia = new Banco("Banco Patagonia", null);
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
		assertFalse(unLocalDeDiarios1.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 12, 30, 30)));
	}

	@Test
	public void testCGPSinServicioDefinidoDiaYHorarioCorrectos() {
		assertTrue(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 10, 15, 33)));
	}

	@Test
	public void testCGPSinServicioDefinidoDiaIncorrectoYHorarioCorrecto() {
		assertFalse(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
	}

	@Test
	public void testCGPConServicioDefinidoCorrectoDiaYHorarioCorrectos() {
		assertTrue(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30), "rentas"));
	}

	@Test
	public void testCGPConServicioDefinidoIncorrectoDiaYHorarioCorrectos() {
		assertFalse(cgpDeFlores.estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30), "multas"));
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
