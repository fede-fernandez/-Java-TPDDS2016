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
	
	private Buscador busqueda;
	
	private List<PuntoDeInteres> puntosDeInteresENcontrados;
	
	
	
	@Before
	public void initialize(){
		
		unBanco = new Banco(null, null, "palabra clave");
		
		unCGP = new CGP("CGP Balbanera", null, "asesoramiento legal", null);
		
		kioscoDeDario = new KioscoDeDiario("kiosco De Diarios");
		unLocal = new LocalComercial(null, null, kioscoDeDario, "palabra clave");
		
		libreriaEscolar = new LibreriaEscolar("libreria escolar");
		unLocal2 = new LocalComercial(null, null, libreriaEscolar, "xxxxxx");
		
		un144_1 = new ParadaDeColectivo("144", null, "palabra clave");
		un144_2 = new ParadaDeColectivo("144", null, "recorrido wilde");
		un144_3 = new ParadaDeColectivo("144", null, "xxxx");
		
		busqueda = new Buscador();
		
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
	public void busqueda_del_144_por_palabra_clave(){

		puntosDeInteresENcontrados.add(un144_2);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("recorrido wilde"));
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
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("kiosco De Diarios"));
	}
	
	@Test
	public void busqueda_por_rubro_de_una_libreria(){

		puntosDeInteresENcontrados.add(unLocal2);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("libreria escolar"));
	}
	
	@Test
	public void busqueda_por_palabra_clave(){

		puntosDeInteresENcontrados.add(un144_1);
		puntosDeInteresENcontrados.add(unLocal);
		puntosDeInteresENcontrados.add(unBanco);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("palabra clave"));
		
	}
	
	
	@Test
	public void busqueda_por_parte_de_un_servicio_CGP(){

		puntosDeInteresENcontrados.add(unCGP);
		
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("asesoramiento legal"));	
	}
	
	

	
	@Test
	public void busqueda_de_nombre_inexistente(){
		Assert.assertEquals(puntosDeInteresENcontrados, busqueda.buscarPorTextoLibre("un nombre inexistente"));
		
	}
	

}
