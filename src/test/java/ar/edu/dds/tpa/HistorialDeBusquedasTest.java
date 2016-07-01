package ar.edu.dds.tpa;


import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.Usuario;



public class HistorialDeBusquedasTest {
	
	
	private HistorialDeBusqueda historial;
	
	private LocalDate cuatroDeFebreroDe2016;
	private LocalDate diezDeEneroDe2016;
	
	private Busqueda busquedaParadaCinco;
	private Busqueda busquedaLibreria;
	private Busqueda busquedaParadaDos;
	
	private List<LocalDate> fechasDeBusqueda;
	
	private Usuario usuario;
	
	
	@Before
	public void inicializar(){
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		
		fechasDeBusqueda = Arrays.asList(cuatroDeFebreroDe2016, diezDeEneroDe2016);
		
		usuario = new Usuario();
		
		busquedaParadaCinco = new Busqueda(usuario,"Parada 5", 10, cuatroDeFebreroDe2016, 5.0);
		busquedaLibreria = new Busqueda(usuario,"Libreria", 20, cuatroDeFebreroDe2016, 5.0);
		busquedaParadaDos = new Busqueda(usuario,"Parada 2", 30, diezDeEneroDe2016, 5.0);
		
		historial = new HistorialDeBusqueda();
		
		historial.agregarBusqueda(busquedaParadaCinco);
		historial.agregarBusqueda(busquedaLibreria);
		historial.agregarBusqueda(busquedaParadaDos);		
		
	}

	@Test
	public void filtradoDeFechasDeBusquedasDistintas() {
		Assert.assertTrue(historial.fechasDeBusquedas().containsAll(fechasDeBusqueda));
	}
	
	@Test
	public void resultadosTotalesSonSesenta(){
		Assert.assertEquals(60, historial.cantidadDeResultadosTotales());
	}
	
	@Test
	public void enCuatroDeFebreroSeRealizaronDosBusquedas(){
		Assert.assertEquals(2, historial.cantidadDeBusquedasEnUnaFecha(cuatroDeFebreroDe2016));
	}

}
