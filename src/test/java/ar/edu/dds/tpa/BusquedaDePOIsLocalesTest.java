package ar.edu.dds.tpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.*;

public class BusquedaDePOIsLocalesTest {
	private Banco unBanco;
	private CGP unCGP;
	private LocalComercial kioscoDeDario;
	private LocalComercial libreriaEscolar;
	private ParadaDeColectivo paradaDel114EnRivadaviaYNazca;
	private ParadaDeColectivo paradaDel114EnPasajeMozart;
	private ParadaDeColectivo paradaDel114EnPrimeraJunta;
	private Mapa mapa;
	private List<PuntoDeInteres> baseDeConocimientosDePuntosDeInteres;
	
	@Before
	public void inicializar(){	
		unBanco = new Banco("Ciudad", null);
		
		unCGP = new CGP("CGP Balvanera", null);
		unCGP.agregarServicio(new Servicio("Rentas"));

		kioscoDeDario = new LocalComercial("El matutino", null, new Rubro("Kiosco de diario", 0));
		
		libreriaEscolar = new LocalComercial("El ateneo", null, new Rubro("Libreria Escolar", 0));
		
		paradaDel114EnRivadaviaYNazca = new ParadaDeColectivo("144 Rivadavia y Nazca", null);
		paradaDel114EnPasajeMozart = new ParadaDeColectivo("144 Pasaje Mozart ", null);
		paradaDel114EnPrimeraJunta = new ParadaDeColectivo("144 Primera Junta", null);
		
		mapa = new Mapa();
		
		mapa.agregarPuntoDeInteres(paradaDel114EnRivadaviaYNazca);
		mapa.agregarPuntoDeInteres(paradaDel114EnPasajeMozart);
		mapa.agregarPuntoDeInteres(paradaDel114EnPrimeraJunta);
		mapa.agregarPuntoDeInteres(kioscoDeDario);
		mapa.agregarPuntoDeInteres(libreriaEscolar);
		mapa.agregarPuntoDeInteres(unBanco);
		mapa.agregarPuntoDeInteres(unCGP);
	
		baseDeConocimientosDePuntosDeInteres = new ArrayList<>();
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnRivadaviaYNazca);
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnPasajeMozart);
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnPrimeraJunta);
		baseDeConocimientosDePuntosDeInteres.add(kioscoDeDario);
		baseDeConocimientosDePuntosDeInteres.add(libreriaEscolar);
		baseDeConocimientosDePuntosDeInteres.add(unCGP);
	}

	@Test
	public void busquedaDeBancoCiudad(){	
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("Ciudad").size());
	}
	
	@Test
	public void busquedaDeCGPBalvanera(){	
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("Balvanera").size());
	}
	
	@Test
	public void busquedaDeColectivos101(){
		Assert.assertEquals(0, mapa.buscarPorTextoLibre("101").size());
	}
	
	@Test
	public void busquedaDeColectivos114(){	
		Assert.assertEquals(3, mapa.buscarPorTextoLibre("144").size());
	}
	
	@Test
	public void busquedaPorNombreKioscoDeDiarios(){	
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("matutino").size());
	}
	
	@Test
	public void busquedaPorRubroKioscoDeDiarios(){	
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("Kiosco De Diarios").size());
	}
	
	@Test
	public void busquedaPorNombreLibreriaEscolar(){
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("ateneo").size());
	}
	
	@Test
	public void busquedaPorRubroLibreriaEscolar(){
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("libreria escolar").size());
	}
	
	@Test
	public void busquedaPorServicioEnCGP(){
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("Rentas").size());	
	}
	
	@Test
	public void busquedaPorServicioEnMayusculasEnCGP(){
		Assert.assertEquals(1, mapa.buscarPorTextoLibre("RENTAS").size());	
	}
	
	@Test
	public void busquedaPorNombreInexistente(){
		Assert.assertEquals(0, mapa.buscarPorTextoLibre("nombre inexistente").size());	
	}
	
	@Test
	public void busquedaPorPalabraFruta(){
		Assert.assertEquals(0, mapa.buscarPorTextoLibre("fruta").size());	
	}
}