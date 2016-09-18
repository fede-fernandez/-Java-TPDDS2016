package persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.dds.tpa.dataBase.BaseDeDatos;
import ar.edu.dds.tpa.model.*;

public class PersistenciaDeHorariosDisponiblesTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

		List<DayOfWeek> deLunesAViernes;
		ParadaDeColectivo colectivo114;
		LocalComercial unLocalDeDiarios1;
		CGP cgpDeFlores;
		Servicio rentas;
		Servicio multas;
		Servicio depositos;
		Banco bancoPatagonia;
		
		private BaseDeDatos db;

		@Before
		public void inicializar() {
			deLunesAViernes = new ArrayList<DayOfWeek>();
			deLunesAViernes.addAll(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

			colectivo114 = new ParadaDeColectivo("114", null);

			unLocalDeDiarios1 = new LocalComercial("Diarin", null, null);
			unLocalDeDiarios1.agregarHorarioDeAtencionComunEnVariosDias(deLunesAViernes, LocalTime.of(9, 30), LocalTime.of(18, 30));

			rentas = new Servicio("Rentas");
			rentas.agregarHorarioDeAtencion(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(16, 45));

			multas = new Servicio("Multas");
			multas.agregarHorarioDeAtencion(DayOfWeek.THURSDAY, LocalTime.of(12, 15), LocalTime.of(19, 20));

			cgpDeFlores = new CGP("CGPFlores", null);
			cgpDeFlores.agregarServicio(rentas);
			cgpDeFlores.agregarServicio(multas);
			
			depositos = new Servicio("Depositos");
			depositos.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(8, 0), LocalTime.of(11, 30));
			depositos.agregarHorarioDeAtencion(deLunesAViernes, LocalTime.of(14, 15), LocalTime.of(20, 15));
			
			bancoPatagonia = new Banco("Banco Patagonia", null);
			bancoPatagonia.agregarServicio(depositos);
			
			
			db = new BaseDeDatos();
			
		}

		@Test
		public void testParadaDeColectivoDisponibilidad() {
			db.guardar(colectivo114);
			assertTrue(db.obtenerPuntoInteres(colectivo114.getID()).estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
		}

		@Test
		public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaYHorarioCorrectos() {
			db.guardar(unLocalDeDiarios1);
			assertTrue(db.obtenerPuntoInteres(unLocalDeDiarios1.getID()).estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 10, 15, 33)));
		}

		@Test
		public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaIncorrectoYHorarioCorrecto() {
			db.guardar(unLocalDeDiarios1);
			assertFalse(db.obtenerPuntoInteres(unLocalDeDiarios1.getID()).estaDisponibleEn(LocalDateTime.of(2016, 4, 23, 10, 15, 33)));
		}

		@Test
		public void testLocalComercialKioscoDeDiarioDisponibilidadEnDiaCorrectoYHorarioIncorrecto() {
			db.guardar(unLocalDeDiarios1);
			assertFalse(unLocalDeDiarios1.estaDisponibleEn(LocalDateTime.of(2016, 4, 22, 20, 30, 30)));
		}

		@Test
		public void testCGPSinServicioDefinidoDiaYHorarioCorrectos() {
			db.guardar(unLocalDeDiarios1);
			assertTrue(db.obtenerPuntoInteres(unLocalDeDiarios1.getID()).estaDisponibleEn(LocalDateTime.of(2016, 6, 2, 13, 15, 33)));
		}

		@Test
		public void testCGPSinServicioDefinidoDiaIncorrectoYHorarioCorrecto() {
			db.guardar(cgpDeFlores);
			assertFalse(db.obtenerPuntoInteres(cgpDeFlores.getID()).estaDisponibleEn(LocalDateTime.of(2016, 6, 22, 10, 15, 33)));
		}

		@Test
		public void testCGPConServicioDefinidoCorrectoDiaYHorarioCorrectos() {
			db.guardar(cgpDeFlores);
			assertTrue(db.obtenerPuntoDeInteresConServicios(cgpDeFlores.getID()).estaDisponibleEn(LocalDateTime.of(2016, 6, 7, 12, 30, 30), "Rentas"));
		}

		@Test
		public void testCGPConServicioDefinidoIncorrectoDiaYHorarioCorrectos() {
			db.guardar(cgpDeFlores);
			assertFalse(db.obtenerPuntoDeInteresConServicios(cgpDeFlores.getID()).estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30), "Renovaciones"));
		}

		@Test
		public void testBancoEnDiaHabilHorarioCorrecto() {
			db.guardar(bancoPatagonia);
			assertTrue(db.obtenerPuntoInteres(bancoPatagonia.getID()).estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30)));
		}

		@Test
		public void testBancoEnDiaHabilHorarioIncorrecto() {
			db.guardar(bancoPatagonia);
			assertFalse(db.obtenerPuntoInteres(bancoPatagonia.getID()).estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 19, 30, 30)));
		}
	}
	
