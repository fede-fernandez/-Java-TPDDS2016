package ar.edu.dds.tpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.*;

public class BusquedaDePuntoTest {
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
		
		unCGP = new CGP("CGP Balbanera", null);
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
		
	}

	@Test
	public void busquedaDeColectivos114(){
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnRivadaviaYNazca);
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnPasajeMozart);
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnPrimeraJunta);
		Assert.assertEquals(baseDeConocimientosDePuntosDeInteres, mapa.buscarPorTextoLibre("144"));
	}
	
	@Test
	public void busquedaDeColectivos101(){
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnRivadaviaYNazca);
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnPasajeMozart);
		baseDeConocimientosDePuntosDeInteres.add(paradaDel114EnPrimeraJunta);
		Assert.assertNotEquals(baseDeConocimientosDePuntosDeInteres, mapa.buscarPorTextoLibre("101"));
	}
	
	@Test
	public void busquedaPorRubroKioscoDeDiarios(){
		baseDeConocimientosDePuntosDeInteres.add(kioscoDeDario);	
		Assert.assertEquals(baseDeConocimientosDePuntosDeInteres, mapa.buscarPorTextoLibre("Kiosco De Diarios"));
	}
	
	@Test
	public void busquedaPorRubroLibreriaEscolar(){
		baseDeConocimientosDePuntosDeInteres.add(libreriaEscolar);
		Assert.assertEquals(baseDeConocimientosDePuntosDeInteres, mapa.buscarPorTextoLibre("libreria escolar"));
	}
	
	@Test
	public void busquedaPorServicioEnCGP(){
		baseDeConocimientosDePuntosDeInteres.add(unCGP);
		Assert.assertEquals(baseDeConocimientosDePuntosDeInteres, mapa.buscarPorTextoLibre("Rentas"));	
	}

	@Test
	public void busquedaPorNombreInexistente(){
		mapa.buscarPorTextoLibre("Nombre que no existe");
		Assert.assertEquals(0, baseDeConocimientosDePuntosDeInteres.size());	
	}
}