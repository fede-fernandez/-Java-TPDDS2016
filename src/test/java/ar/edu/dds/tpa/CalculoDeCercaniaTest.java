package ar.edu.dds.tpa;
import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import ar.edu.dds.tpa.model.*;

public class CalculoDeCercaniaTest {
	Point posicionDeUsuario1;
	Point posicionDeUsuario2;
	Point posicionDeUsuario3;
	Point posicionDeUsuario4;
	ParadaDeColectivo bondi114;
	KioscoDeDiario kioscoDeDarioVerde1;
	LocalComercial unLocalDeDiario1;
	CGP cgpDeFlores;
	Servicio rentas;
	Servicio multas;
	List<Point> puntos;
	Polygon comunaDeFlores;
	Banco bancoPatagonia;

	@Before
	public void inicializar() {
		bondi114 = new ParadaDeColectivo("114", new Point(200.0006, 100.0));
		
		kioscoDeDarioVerde1 = new KioscoDeDiario(null);
		
		
		unLocalDeDiario1 = new LocalComercial("Diarin", new Point(100.000002, 50.0), kioscoDeDarioVerde1);
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.MONDAY, new RangoDeHorario(LocalTime.of(9, 0), LocalTime.of(18, 0)));
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.TUESDAY, new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(17, 0)));
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY, new RangoDeHorario(LocalTime.of(10, 30), LocalTime.of(19, 0)));
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, new RangoDeHorario(LocalTime.of(8, 0), LocalTime.of(11, 0)));
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, new RangoDeHorario(LocalTime.of(14, 0), LocalTime.of(19, 0)));
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.FRIDAY, new RangoDeHorario(LocalTime.of(9, 0), LocalTime.of(12, 15)));
		unLocalDeDiario1.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, new RangoDeHorario(LocalTime.of(13, 30), LocalTime.of(18, 0)));
		
		rentas = new Servicio("rentas");
		rentas.agregarDiaYHorarioDeAtencion(DayOfWeek.MONDAY, new RangoDeHorario(LocalTime.of(9, 0), LocalTime.of(18, 0)));
		rentas.agregarDiaYHorarioDeAtencion(DayOfWeek.TUESDAY, new RangoDeHorario(LocalTime.of(10, 0), LocalTime.of(17, 0)));
		
		multas = new Servicio("multas");
		multas.agregarDiaYHorarioDeAtencion(DayOfWeek.WEDNESDAY, new RangoDeHorario(LocalTime.of(10, 30), LocalTime.of(19, 0)));
		multas.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, new RangoDeHorario(LocalTime.of(8, 0), LocalTime.of(11, 0)));
		multas.agregarDiaYHorarioDeAtencion(DayOfWeek.THURSDAY, new RangoDeHorario(LocalTime.of(14, 0), LocalTime.of(19, 0)));
		
		puntos = new ArrayList<Point>(Arrays.asList(new Point(10.0,10.0), new Point(10.0,-10.0), new Point(-10.0,10.0), new Point(-10.0,-10.0)));
		comunaDeFlores = new Polygon(puntos);
		cgpDeFlores = new CGP("CGPFlores", new Point(100.0, 5.0), comunaDeFlores);
		cgpDeFlores.agregarServicio(rentas);
		cgpDeFlores.agregarServicio(multas);
		
		bancoPatagonia = new Banco("Banco Patagonia", new Point(10.0, 10.0));
		
		posicionDeUsuario1 = new Point(100.000001,50.0);
		posicionDeUsuario2 = new Point(200.0005,100.0);
		posicionDeUsuario3 = new Point(5.0, 5.0);
		posicionDeUsuario4 = new Point(100.0, 100.0);
	}
	
	@Test
	public void elPrimerUsuarioEstaCercaDelKiosco(){
		assertTrue(unLocalDeDiario1.estaCercaDe(posicionDeUsuario1));
	}
	
	
	@Test
	public void elPrimerUsuarioNoEstaCercaDeLaLibreria(){
		assertFalse(bondi114.estaCercaDe(posicionDeUsuario1));		
	}
	
	
	@Test
	public void elSegundoUsuarioEstaCercaDeLaLibreria(){
		assertTrue(bondi114.estaCercaDe(posicionDeUsuario2));
	}
	
	@Test
	public void elUsuarioTresEstaCercaDelCGP(){
		assertTrue(cgpDeFlores.estaCercaDe(posicionDeUsuario3));
	}
	
	@Test
	public void elUsuarioCuatroNoEstaCercaDelCGP(){
		assertFalse(cgpDeFlores.estaCercaDe(posicionDeUsuario4));
	}
}
