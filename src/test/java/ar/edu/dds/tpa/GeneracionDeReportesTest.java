package ar.edu.dds.tpa;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import ar.edu.dds.tpa.excepcion.FaltaDePermisosExcepcion;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.model.BusquedaRealizada;;

public class GeneracionDeReportesTest {

	
	Administrador administrador;	
	Terminal terminalAbasto;	
	BusquedaRealizada busquedaDeBanco;
	BusquedaRealizada busquedaDeColectivo;
	BusquedaRealizada busquedaDe180;
	BusquedaRealizada busquedaDeHaceUnMes;
	LocalDate cuatroDeFebreroDe2016;
	List<Integer> resultadosParciales;
	
	
	@Before
	public void inicializar(){
		administrador = new Administrador(null);		
		terminalAbasto = new Terminal("Terminal Abasto", null);		
		administrador.agregarTerminal(terminalAbasto);
		administrador.agregarMapaATerminales();
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		resultadosParciales = new ArrayList<Integer>();
		resultadosParciales.add(3);
		resultadosParciales.add(1);
		
		
		busquedaDeBanco = new BusquedaRealizada("Banco", 1, cuatroDeFebreroDe2016, 5);
		busquedaDeColectivo = new BusquedaRealizada("colectivo", 3, cuatroDeFebreroDe2016, 7);
		busquedaDe180 = new BusquedaRealizada("colectivo", 1, cuatroDeFebreroDe2016, 2);
		terminalAbasto.agregarBusquedaRealizada(busquedaDeBanco);
		terminalAbasto.agregarBusquedaRealizada(busquedaDeColectivo);
		terminalAbasto.agregarBusquedaRealizada(busquedaDe180);
		terminalAbasto.activarReportes();
		
		
	}
	
	@Test
	public void cantidadDeBusquedasRealizadasEl4DeFebreroDe2016EsTresTest() throws FaltaDePermisosExcepcion{
		Assert.assertEquals(3, administrador.obtenerReporteDeBusquedaPorFecha(terminalAbasto, cuatroDeFebreroDe2016));
	}
	
	@Test
	public void resultadosParcialesDeBancoEsUnoTest() throws FaltaDePermisosExcepcion{
		Assert.assertTrue(administrador.obtenerReporteDeResultadosParcialesDeUnaBusqueda(terminalAbasto, "Banco").contains(1));
	}
	
	@Test
	public void resultadosParcialesDeColectivoSonTresyUnoTest() throws FaltaDePermisosExcepcion{
		Assert.assertTrue(administrador.obtenerReporteDeResultadosParcialesDeUnaBusqueda(terminalAbasto, "colectivo").containsAll(resultadosParciales));
	}
	
	@Test
	public void cantidadDeResultadosTotalesDeColectivoSonCuatroTest() throws FaltaDePermisosExcepcion{
		Assert.assertEquals(4,administrador.obtenerReporteDeResultadosTotalesDeUnaBusqueda(terminalAbasto, "colectivo"));
	}
	
	@Test (expected = FaltaDePermisosExcepcion.class)
	public void excepcionDeFaltaDePermisos() throws FaltaDePermisosExcepcion {
		terminalAbasto.desactivarReportes();
		administrador.obtenerReporteDeResultadosParcialesDeUnaBusqueda(terminalAbasto, "Banco");
	}
}
