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
	private LocalComercial unLocal;
	private KioscoDeDiario kioscoDeDario;
	private LocalComercial unLocal2;
	private LibreriaEscolar libreriaEscolar;
	private ParadaDeColectivo un144_1;
	private ParadaDeColectivo un144_2;
	private ParadaDeColectivo un144_3;
	private Mapa busqueda;
	private List<PuntoDeInteres> puntosDeInteresEncontrados;
	
	@Before
	public void inicializar(){	
		unBanco = new Banco("Ciudad", null);
		
		unCGP = new CGP("CGP Balbanera", null, null);
		unCGP.agregarServicio(new Servicio("Rentas"));
		
		kioscoDeDario = new KioscoDeDiario();
		unLocal = new LocalComercial("El matutino", null, kioscoDeDario);
		
		libreriaEscolar = new LibreriaEscolar();
		unLocal2 = new LocalComercial("El ateneo", null, libreriaEscolar);
		
		un144_1 = new ParadaDeColectivo("144", null);
		un144_2 = new ParadaDeColectivo("144", null);
		un144_3 = new ParadaDeColectivo("144", null);
		
		busqueda = new Mapa();
		
		busqueda.agregarPuntoDeInteres(un144_1);
		busqueda.agregarPuntoDeInteres(un144_2);
		busqueda.agregarPuntoDeInteres(un144_3);
		busqueda.agregarPuntoDeInteres(unLocal);
		busqueda.agregarPuntoDeInteres(unLocal2);
		busqueda.agregarPuntoDeInteres(unBanco);
		busqueda.agregarPuntoDeInteres(unCGP);
	
		puntosDeInteresEncontrados = new ArrayList<>();
		
	}

	@Test
	public void busquedaDeColectivos114(){
		puntosDeInteresEncontrados.add(un144_1);
		puntosDeInteresEncontrados.add(un144_2);
		puntosDeInteresEncontrados.add(un144_3);
		Assert.assertEquals(puntosDeInteresEncontrados, busqueda.buscarPorTextoLibre("144"));
	}
	
	@Test
	public void busquedaDeColectivos101(){
		puntosDeInteresEncontrados.add(un144_1);
		puntosDeInteresEncontrados.add(un144_2);
		puntosDeInteresEncontrados.add(un144_3);
		Assert.assertNotEquals(puntosDeInteresEncontrados, busqueda.buscarPorTextoLibre("101"));
	}
	
	@Test
	public void busquedaPorRubroKioscoDeDiarios(){
		puntosDeInteresEncontrados.add(unLocal);	
		Assert.assertEquals(puntosDeInteresEncontrados, busqueda.buscarPorTextoLibre("Kiosco De Diarios"));
	}
	
	@Test
	public void busquedaPorRubroLibreriaEscolar(){
		puntosDeInteresEncontrados.add(unLocal2);
		Assert.assertEquals(puntosDeInteresEncontrados, busqueda.buscarPorTextoLibre("libreria escolar"));
	}
	
	@Test
	public void busquedaPorServicioEnCGP(){
		puntosDeInteresEncontrados.add(unCGP);
		Assert.assertEquals(puntosDeInteresEncontrados, busqueda.buscarPorTextoLibre("Rentas"));	
	}

	@Test
	public void busquedaPorNombreInexistente(){
		busqueda.buscarPorTextoLibre("Nombre que no existe");
		Assert.assertEquals(0, puntosDeInteresEncontrados.size());	
	}
}