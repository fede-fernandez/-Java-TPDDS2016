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
	private Buscador buscador;
	
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
		
		buscador = new Buscador(mapa);
	}

	@Test
	public void busquedaDeBancoCiudad(){
		Assert.assertEquals(1, buscador.buscar("Ciudad", null).size());
	}
	
	@Test
	public void busquedaDeCGPBalvanera(){	
		Assert.assertEquals(1, buscador.buscar("Balvanera", null).size());
	}
	
	@Test
	public void busquedaDeColectivos101(){
		Assert.assertEquals(0, buscador.buscar("101", null).size());
	}
	
	@Test
	public void busquedaDeColectivos114(){	
		Assert.assertEquals(3, buscador.buscar("144", null).size());
	}
	
	@Test
	public void busquedaPorNombreKioscoDeDiarios(){	
		Assert.assertEquals(1, buscador.buscar("matutino", null).size());
	}
	
	@Test
	public void busquedaPorRubroKioscoDeDiarios(){	
		Assert.assertEquals(1, buscador.buscar("Kiosco De Diarios", null).size());
	}
	
	@Test
	public void busquedaPorNombreLibreriaEscolar(){
		Assert.assertEquals(1, buscador.buscar("ateneo", null).size());
	}
	
	@Test
	public void busquedaPorRubroLibreriaEscolar(){
		Assert.assertEquals(1, buscador.buscar("libreria escolar", null).size());
	}
	
	@Test
	public void busquedaPorServicioEnCGP(){
		Assert.assertEquals(1, buscador.buscar("Rentas", null).size());	
	}
	
	@Test
	public void busquedaPorServicioEnMayusculasEnCGP(){
		Assert.assertEquals(1, buscador.buscar("RENTAS", null).size());	
	}
	
	@Test
	public void busquedaPorNombreInexistente(){
		Assert.assertEquals(0, buscador.buscar("nombre inexistente", null).size());	
	}
	
	@Test
	public void busquedaPorPalabraFruta(){
		Assert.assertEquals(0, buscador.buscar("fruta", null).size());	
	}
}