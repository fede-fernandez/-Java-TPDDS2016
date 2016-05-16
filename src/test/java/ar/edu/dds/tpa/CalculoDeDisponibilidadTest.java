package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import ar.edu.dds.tpa.model.*;

public class CalculoDeDisponibilidadTest {
	ParadaDeColectivo bondi114;
	DiaYHorarioDeAtencion lun9a18;
	DiaYHorarioDeAtencion mar10a17;
	DiaYHorarioDeAtencion mier1030a19;
	DiaYHorarioDeAtencion jue8a11y14a19;
	DiaYHorarioDeAtencion vier9a1215y1330a18;
	RangoDeHorario de9a18;
	RangoDeHorario de10a17;
	RangoDeHorario de1030a19;
	RangoDeHorario de8a11;
	RangoDeHorario de14a19;
	RangoDeHorario de9a1215;
	RangoDeHorario de1330a18;
	LocalComercial unLocalDeDiarios1;
	CGP cgpDeFlores;
	Servicio rentas;
	Servicio multas;
	Banco bancoPatagonia;

	@Before
	public void inicializar() {
		bondi114 = new ParadaDeColectivo("114", null);
		
		de9a18 = new RangoDeHorario(LocalTime.of(9, 0), LocalTime.of(18, 0));
		de10a17 = new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(17, 0));
		de1030a19 = new RangoDeHorario(LocalTime.of(10, 30), LocalTime.of(19, 0));
		de8a11 = new RangoDeHorario(LocalTime.of(8, 00), LocalTime.of(11, 0));
		de14a19 = new RangoDeHorario(LocalTime.of(14, 00), LocalTime.of(19, 0));
		de9a1215 = new RangoDeHorario(LocalTime.of(9, 00), LocalTime.of(12, 15));
		de1330a18 = new RangoDeHorario(LocalTime.of(13, 30), LocalTime.of(18, 00));
		
		lun9a18 = new DiaYHorarioDeAtencion(DayOfWeek.MONDAY);
		lun9a18.agregarRangoDeHorario(de9a18);
		mar10a17 = new DiaYHorarioDeAtencion(DayOfWeek.TUESDAY);
		mar10a17.agregarRangoDeHorario(de10a17);
		mier1030a19 = new DiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY);
		mier1030a19.agregarRangoDeHorario(de1030a19);
		jue8a11y14a19 = new DiaYHorarioDeAtencion(DayOfWeek.THURSDAY);
		jue8a11y14a19.agregarRangoDeHorario(de8a11);
		jue8a11y14a19.agregarRangoDeHorario(de14a19);
		vier9a1215y1330a18 = new DiaYHorarioDeAtencion(DayOfWeek.FRIDAY);
		vier9a1215y1330a18.agregarRangoDeHorario(de9a1215);
		vier9a1215y1330a18.agregarRangoDeHorario(de1330a18);
		
		unLocalDeDiarios1 = new LocalComercial("Diarin", null, null);
		unLocalDeDiarios1.agregarDiaYHorarioDeAtencion(lun9a18);
		unLocalDeDiarios1.agregarDiaYHorarioDeAtencion(mar10a17);
		unLocalDeDiarios1.agregarDiaYHorarioDeAtencion(mier1030a19);
		unLocalDeDiarios1.agregarDiaYHorarioDeAtencion(jue8a11y14a19);
		unLocalDeDiarios1.agregarDiaYHorarioDeAtencion(vier9a1215y1330a18);
		
		rentas = new Servicio("rentas");
		rentas.agregarDiaYHorarioDeAtencion(lun9a18);
		rentas.agregarDiaYHorarioDeAtencion(mar10a17);
		
		multas = new Servicio("multas");
		multas.agregarDiaYHorarioDeAtencion(mier1030a19);
		multas.agregarDiaYHorarioDeAtencion(jue8a11y14a19);
		
		cgpDeFlores = new CGP("CGPFlores", null, null);
		cgpDeFlores.agregarServicio(rentas);
		cgpDeFlores.agregarServicio(multas);
		
		bancoPatagonia = new Banco("Banco Patagonia", null);
	}
	
	@Test
	public void testParadaDeColectivoDisponibilidad() {
		assertTrue(bondi114.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
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