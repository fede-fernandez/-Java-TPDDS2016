package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.Usuario;

public class GeneracionDeReportesTest {

	private HistorialDeBusqueda historialDeBusqueda;
	
	private LocalDate cuatroDeFebreroDe2016;
	private LocalDate diezDeEneroDe2016;
	
	private Busqueda busquedaParadaCinco;
	private Busqueda busquedaLibreria;
	private Busqueda busquedaParadaDos;
	
	private Usuario usuario;
	
	@Before
	public void inicializar(){
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		
		usuario = new Usuario();
		
		busquedaParadaCinco = new Busqueda(usuario,"Parada 5", 10, cuatroDeFebreroDe2016, 5.0);
		busquedaLibreria = new Busqueda(usuario,"Libreria", 20, cuatroDeFebreroDe2016, 5.0);
		busquedaParadaDos = new Busqueda(usuario,"Parada 2", 30, diezDeEneroDe2016, 5.0);
		
		historialDeBusqueda = new HistorialDeBusqueda();
		
		historialDeBusqueda.agregar(busquedaParadaCinco);
		historialDeBusqueda.agregar(busquedaLibreria);
		historialDeBusqueda.agregar(busquedaParadaDos);		
		
	}
	
	@Test
	public void reporteQueTotalizaLasBusquedasPorFecha() {
		assertEquals(2, historialDeBusqueda.cantidadDeBusquedasEnUnaFecha(cuatroDeFebreroDe2016));			
	}
}