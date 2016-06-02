package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import ar.edu.dds.tpa.model.*;
import componentes_externos.BuscadorDeCGPLento;
import componentes_externos.BuscadorDeCGPLento_Impostor;
import componentes_externos.CentroDTO;
import componentes_externos.RangosServicioDTO;
import componentes_externos.ServicioDTO;

public class BusquedaDePuntoTest {

	// Busqueda de puntos de interes
	
	private Banco unBanco;
	
	private CGP unCGP;
	
	private LocalComercial unLocal;
	private KioscoDeDiario kioscoDeDario;
	
	private LocalComercial unLocal2;
	private LibreriaEscolar libreriaEscolar;


	private ParadaDeColectivo un144_1;
	private ParadaDeColectivo un144_2;
	private ParadaDeColectivo un144_3;
	
	private Mapa mapa;
	
	private List<PuntoDeInteres> puntosDeInteresENcontrados;
	
	private BuscadorDeCGPLento_Impostor buscadorDeCGPLento;

	private CentroDTO centroDTO;

	private ServicioDTO servicioDTO;
	
	
	@Before
	public void initialize(){
		
		unBanco = new Banco("Ciudad", null);
		
		unCGP = new CGP("CGP Balbanera", null, null);
		unCGP.agregarServicio(new Servicio("Rentas"));
		
		kioscoDeDario = new KioscoDeDiario("Kiosco De Diarios");
		unLocal = new LocalComercial("El matutino", null, kioscoDeDario);
		
		libreriaEscolar = new LibreriaEscolar("libreria escolar");
		unLocal2 = new LocalComercial("El ateneo", null, libreriaEscolar);
		
		un144_1 = new ParadaDeColectivo("144", null);
		un144_2 = new ParadaDeColectivo("144", null);
		un144_3 = new ParadaDeColectivo("144", null);
		
		mapa = new Mapa();
		
		mapa.agregarPuntoInteres(un144_1);
		mapa.agregarPuntoInteres(un144_2);
		mapa.agregarPuntoInteres(un144_3);
		mapa.agregarPuntoInteres(unLocal);
		mapa.agregarPuntoInteres(unLocal2);
		mapa.agregarPuntoInteres(unBanco);
		mapa.agregarPuntoInteres(unCGP);
	
		puntosDeInteresENcontrados = new ArrayList<>();
		
		
		
		servicioDTO = new ServicioDTO();
		servicioDTO.addRangoServicioDTO(new RangosServicioDTO(1,1,1,1,1));
		
		centroDTO = new CentroDTO();
		centroDTO.setComuna(1);
		centroDTO.setZona("Zona de prueba");
		centroDTO.setDirector("Don Pepito");
		centroDTO.setDomicilioCompleto("Calle Falsa 123");
		centroDTO.setTelefono("555-5555");
		centroDTO.addServiciosDTO(servicioDTO);
		
		
		buscadorDeCGPLento = new BuscadorDeCGPLento_Impostor();
		buscadorDeCGPLento.agregarCentro(centroDTO);
		
		
		
		mapa.agregarBuscadorExterno(buscadorDeCGPLento);
	}
	
	
	@Test
	public void busqueda_de_todos_los_144(){

		puntosDeInteresENcontrados.add(un144_1);
		puntosDeInteresENcontrados.add(un144_2);
		puntosDeInteresENcontrados.add(un144_3);
		
		Assert.assertEquals(puntosDeInteresENcontrados, mapa.buscarPorTextoLibre("144"));
	}
	
	@Test
	public void busqueda_por_rubro(){

		puntosDeInteresENcontrados.add(unLocal);
		
		Assert.assertEquals(puntosDeInteresENcontrados, mapa.buscarPorTextoLibre("Kiosco De Diarios"));
	}
	
	@Test
	public void busqueda_por_rubro_de_una_libreria(){

		puntosDeInteresENcontrados.add(unLocal2);
		
		Assert.assertEquals(puntosDeInteresENcontrados, mapa.buscarPorTextoLibre("libreria escolar"));
	}
	
	
	@Test
	public void busqueda_por_parte_de_un_servicio_CGP(){

		puntosDeInteresENcontrados.add(unCGP);
		
		Assert.assertEquals(puntosDeInteresENcontrados, mapa.buscarPorTextoLibre("Rentas"));	
	}
	
	

	
	@Test
	public void busqueda_de_nombre_inexistente(){
		Assert.assertEquals(puntosDeInteresENcontrados, mapa.buscarPorTextoLibre("un nombre inexistente"));
		
	}
	
	@Test
	public void busqueda_de_cgp_en_buscador_externo_lento_con_resultado() throws Exception {
		mapa.buscarPorTextoLibre("1");
		Assert.assertTrue(mapa.getResultadosExternos().contains(centroDTO.toPuntoDeInteres()));
	}
	
	@Test
	public void busqueda_de_cgp_en_buscador_externo_lento_sin_resultado() throws Exception {
		mapa.buscarPorTextoLibre("2");
		Assert.assertTrue(mapa.getResultadosExternos().isEmpty());
	}
}
