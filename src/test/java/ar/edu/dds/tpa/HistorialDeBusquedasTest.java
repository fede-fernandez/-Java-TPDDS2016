package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import ar.edu.dds.tpa.model.BusquedaRealizada;
import ar.edu.dds.tpa.model.HistorialDeBusquedas;
import ar.edu.dds.tpa.model.Terminal;


public class HistorialDeBusquedasTest {
	
	
	private HistorialDeBusquedas historial;
	
	private LocalDate cuatroDeFebreroDe2016;
	private LocalDate diezDeEneroDe2016;
	
	private BusquedaRealizada busquedaParadaCinco;
	private BusquedaRealizada busquedaLibreria;
	private BusquedaRealizada busquedaParadaDos;
	
	private List<LocalDate> fechasDeBusqueda;
	
	
	@Before
	public void inicializar(){
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		
		fechasDeBusqueda = Arrays.asList(cuatroDeFebreroDe2016, diezDeEneroDe2016);
		
		busquedaParadaCinco = new BusquedaRealizada("Parada 5", 10, cuatroDeFebreroDe2016, 5.0);
		busquedaLibreria = new BusquedaRealizada("Libreria", 20, cuatroDeFebreroDe2016, 5.0);
		busquedaParadaDos = new BusquedaRealizada("Parada 2", 30, diezDeEneroDe2016, 5.0);
		
		historial = new HistorialDeBusquedas();
		
		historial.registrarBusqueda(busquedaParadaCinco);
		historial.registrarBusqueda(busquedaLibreria);
		historial.registrarBusqueda(busquedaParadaDos);
		
		
		
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
