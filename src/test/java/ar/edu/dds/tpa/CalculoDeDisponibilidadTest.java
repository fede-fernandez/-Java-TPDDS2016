package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.model.*;

public class CalculoDeDisponibilidadTest {
	
	ParadaDeColectivo bondi114;
	
	KioscoDeDiario kioscoDeDarioVerde1;
	
	RangoDeHorario de8a11;
	RangoDeHorario de9a1215;
	RangoDeHorario de9a18;
	RangoDeHorario de10a15;
	RangoDeHorario de10a17;
	RangoDeHorario de1030a19;
	RangoDeHorario de1330a18;
	RangoDeHorario de14a19;
	
	LocalComercial unLocalDeDiario1;
	
	CGP cgpDeFlores;
	Servicio rentas;
	Servicio multas;
	
	List<Point> puntos;
	
	Polygon comunaDeFlores;
	
	Banco bancoPatagonia;

	@Before
	public void inicializar() {
		bondi114 = new ParadaDeColectivo("114", new Point(10.0, 10.0));
		
		kioscoDeDarioVerde1 = new KioscoDeDiario(null);
		
		de9a18 = new RangoDeHorario(LocalTime.of(9, 0), LocalTime.of(18, 0));
		de10a17 = new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(17, 0));
		de1030a19 = new RangoDeHorario(LocalTime.of(10, 30), LocalTime.of(19, 0));
		de8a11 = new RangoDeHorario(LocalTime.of(8, 00), LocalTime.of(11, 0));
		de14a19 = new RangoDeHorario(LocalTime.of(14, 00), LocalTime.of(19, 0));
		de9a1215 = new RangoDeHorario(LocalTime.of(9, 00), LocalTime.of(12, 15));
		de1330a18 = new RangoDeHorario(LocalTime.of(13, 30), LocalTime.of(18, 00));
		de10a15 = new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(15, 0));
		
		
		unLocalDeDiario1 = new LocalComercial("Diarin", new Point(10.0, 10.0), kioscoDeDarioVerde1);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.MONDAY, de9a18);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.TUESDAY, de10a17);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY, de1030a19);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, de8a11);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, de14a19);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.FRIDAY, de9a1215);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.FRIDAY, de1330a18);
		
		rentas = new Servicio("rentas");
		rentas.agregarDiaYHorarioDeAtencion(DayOfWeek.MONDAY, de9a18);
		rentas.agregarDiaYHorarioDeAtencion(DayOfWeek.TUESDAY, de10a17);
		
		multas = new Servicio("multas");
		multas.agregarDiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY, de1030a19);
		multas.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, de8a11);
		multas.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, de14a19);
		
		puntos = new ArrayList<Point>(Arrays.asList(new Point(10.0,10.0), new Point(10.0,-10.0), new Point(-10.0,10.0), new Point(-10.0,-10.0)));
		comunaDeFlores = new Polygon(puntos);
		cgpDeFlores = new CGP("CGPFlores", new Point(100.0, 5.0), comunaDeFlores);
		cgpDeFlores.agregarServicio(rentas);
		cgpDeFlores.agregarServicio(multas);
		
		bancoPatagonia = new Banco("Banco Patagonia", new Point(10.0, 10.0));
		bancoPatagonia.agregarDiaYHorarioDeAtencion(DayOfWeek.MONDAY, de10a15);
		bancoPatagonia.agregarDiaYHorarioDeAtencion(DayOfWeek.TUESDAY, de10a15);
		bancoPatagonia.agregarDiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY, de10a15);
		bancoPatagonia.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, de10a15);
		bancoPatagonia.agregarDiaYHorarioDeAtencion(DayOfWeek.FRIDAY, de10a15);
	}
	
	@Test
	public void testParadaDeColectivoDisponibilidad() {
		assertTrue(bondi114.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
	}
	
	@Test
	public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaYHorarioCorrectos() {
		assertTrue(unLocalDeDiario1.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
	}

	@Test
	public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaIncorrectoYHorarioCorrecto() {
		assertFalse(unLocalDeDiario1.estaDisponibleEn(LocalDateTime.of(2016, 4, 23, 10, 15, 33)));
	}
	
	@Test
	public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaCorrectoYHorarioIncorrecto() {
		assertFalse(unLocalDeDiario1.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 12, 30, 30)));
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
