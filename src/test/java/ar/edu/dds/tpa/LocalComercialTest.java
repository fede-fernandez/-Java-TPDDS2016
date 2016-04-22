package ar.edu.dds.tpa;


import static org.junit.Assert.*;

import org.uqbar.geodds.*;

import org.junit.*;

import ar.edu.dds.tpa.model.*;



public class LocalComercialTest {
	
	LocalComercial kioscoDePepe;
	LocalComercial libreriaElLider;
	
	KioscoDeDiario kiosco;
	LibreriaEscolar libreria;
		
	Direccion direccionDeKiosco;
	Direccion direccionDeLibreria;
	
	Point coordenadasDeKiosco;
	Point coordenadasDeLibreria;
	
	Point posicionDeUsuario1;
	Point posicionDeUsuario2;
	
	@Before
	public void inicializar(){
		
		direccionDeKiosco = new Direccion("Alberdi", 1600);
		direccionDeLibreria = new Direccion("Rivadavia", 3000);
		
		coordenadasDeKiosco = new Point(100.000002,50.0);
		coordenadasDeLibreria = new Point(200.0006,100.0);
		
		posicionDeUsuario1 = new Point(100.000001,50.0);
		posicionDeUsuario2 = new Point(200.0005,100.0);
		
		kiosco = new KioscoDeDiario();
		libreria = new LibreriaEscolar();
		
		kioscoDePepe = new LocalComercial("Kiosco de Pepe", direccionDeKiosco, coordenadasDeKiosco, kiosco);
		
		libreriaElLider = new LocalComercial("Libreria El Lider + 4", direccionDeLibreria, coordenadasDeLibreria, libreria);
		
		
	}
	
	@Test
	public void elPrimerUsuarioEstaCercaDelKiosco(){
		assertTrue(kioscoDePepe.estaCercaDe(posicionDeUsuario1));
	}
	
	
	@Test
	public void elPrimerUsuarioNoEstaCercaDeLaLibreria(){
		assertFalse(libreriaElLider.estaCercaDe(posicionDeUsuario1));		
	}
	
	
	@Test
	public void elSegundoUsuarioEstaCercaDeLaLibreria(){
		assertTrue(libreriaElLider.estaCercaDe(posicionDeUsuario2));
	}
	
}
