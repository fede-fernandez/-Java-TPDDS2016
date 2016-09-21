package persistence;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.dataBase.BaseDeDatos;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;

public class BusquedaDePOIsPersistidosTest {
	private Banco unBanco;
	private CGP unCGP;
	private LocalComercial kioscoDeDario;
	private LocalComercial libreriaEscolar;
	private ParadaDeColectivo paradaDel114EnRivadaviaYNazca;
	private ParadaDeColectivo paradaDel114EnPasajeMozart;
	private ParadaDeColectivo paradaDel114EnPrimeraJunta;
	private Mapa mapa;
	private List<LocalComercial> listaPOIs;
	private Buscador buscador;
	
	private BaseDeDatos db;
	
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
		
		db = new BaseDeDatos();
		
//		db.guardar(unBanco);
//		db.guardar(unCGP);
		db.guardar(kioscoDeDario);
		db.guardar(libreriaEscolar);
//		db.guardar(paradaDel114EnRivadaviaYNazca);
//		db.guardar(paradaDel114EnPasajeMozart);
//		db.guardar(paradaDel114EnPrimeraJunta);
		
		listaPOIs = db.todosPOIs();
		
//		mapa.agregarListaPuntoDeInteres(listaPOIs);
//		
//		buscador = new Buscador(mapa);
		
		
		
	}
	
	@Test
	public void hayAlgo(){
		Assert.assertEquals(0, mapa.obtenerPuntosDeInteres().size());
	}

//	@Test
//	public void busquedaDeBancoCiudad(){
//		Assert.assertEquals(1, buscador.buscar("Ciudad", null).size());
//	}
//	
//	@Test
//	public void busquedaDeCGPBalvanera(){	
//		Assert.assertEquals(1, buscador.buscar("Balvanera", null).size());
//	}
//	
//	@Test
//	public void busquedaDeColectivos101(){
//		Assert.assertEquals(0, buscador.buscar("101", null).size());
//	}
//	
//	@Test
//	public void busquedaDeColectivos114(){	
//		Assert.assertEquals(3, buscador.buscar("144", null).size());
//	}
//	
//	@Test
//	public void busquedaPorNombreKioscoDeDiarios(){	
//		Assert.assertEquals(1, buscador.buscar("matutino", null).size());
//	}
//	
//	@Test
//	public void busquedaPorRubroKioscoDeDiarios(){	
//		Assert.assertEquals(1, buscador.buscar("Kiosco De Diarios", null).size());
//	}
//	
//	@Test
//	public void busquedaPorNombreLibreriaEscolar(){
//		Assert.assertEquals(1, buscador.buscar("ateneo", null).size());
//	}
//	
//	@Test
//	public void busquedaPorRubroLibreriaEscolar(){
//		Assert.assertEquals(1, buscador.buscar("libreria escolar", null).size());
//	}
//	
//	@Test
//	public void busquedaPorServicioEnCGP(){
//		Assert.assertEquals(1, buscador.buscar("Rentas", null).size());	
//	}
//	
//	@Test
//	public void busquedaPorServicioEnMayusculasEnCGP(){
//		Assert.assertEquals(1, buscador.buscar("RENTAS", null).size());	
//	}
//	
//	@Test
//	public void busquedaPorNombreInexistente(){
//		Assert.assertEquals(0, buscador.buscar("nombre inexistente", null).size());	
//	}
//	
//	@Test
//	public void busquedaPorPalabraFruta(){
//		Assert.assertEquals(0, buscador.buscar("fruta", null).size());	
//	}
	
	
}
