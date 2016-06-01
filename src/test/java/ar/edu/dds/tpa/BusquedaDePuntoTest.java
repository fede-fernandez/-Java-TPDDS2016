package ar.edu.dds.tpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import ar.edu.dds.tpa.model.*;

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
	
	private Mapa busqueda;
	
	private List<PuntoDeInteres> puntosDeInteresENcontrados;
	
	
	
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
		
		busqueda = new Mapa();
		
		busqueda.agregarPuntoInteres(un144_1);
		busqueda.agregarPuntoInteres(un144_2);
		busqueda.agregarPuntoInteres(un144_3);
		busqueda.agregarPuntoInteres(unLocal);
		busqueda.agregarPuntoInteres(unLocal2);
		busqueda.agregarPuntoInteres(unBanco);
		busqueda.agregarPuntoInteres(unCGP);
	
		puntosDeInteresENcontrados = new ArrayList<>();
		
	}
	
	
	@Test
	public void busqueda_de_todos_los_144(){

		puntosDeInteresENcontrados.add(un144_1);
		puntosDeInteresENcontrados.add(un144_2);
		puntosDeInteresENcontrados.add(un144_3);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("144"));
	}
	
	@Test
	public void busqueda_por_rubro(){

		puntosDeInteresENcontrados.add(unLocal);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("Kiosco De Diarios"));
	}
	
	@Test
	public void busqueda_por_rubro_de_una_libreria(){

		puntosDeInteresENcontrados.add(unLocal2);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("libreria escolar"));
	}
	
	
	@Test
	public void busqueda_por_parte_de_un_servicio_CGP(){

		puntosDeInteresENcontrados.add(unCGP);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("Rentas"));	
	}
	
	

	
	@Test
	public void busqueda_de_nombre_inexistente(){
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("un nombre inexistente"));
		
	}
	

}
