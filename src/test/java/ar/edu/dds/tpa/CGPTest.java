package ar.edu.dds.tpa;


import static org.junit.Assert.*;

import java.util.*;

import org.uqbar.geodds.*;

import org.junit.*;

import ar.edu.dds.tpa.model.*;

public class CGPTest {

	Geolocalizacion coordenadasCGPAlgunLugar;
	Geolocalizacion posicionDeUsuario1;
	Geolocalizacion posicionDeUsuario2;
	
	Comuna comunaDeAlgunLugar;
	
	Direccion direccionDeCGPAlgunLugar;
	
	CGP cgpAlgunLugar;
	
	List<Point> puntos;
	
	
	
	@Before
	
	public void inicializar(){
		
		puntos = new ArrayList<Point>(Arrays.asList(new Point(10.0,10.0), new Point(10.0,-10.0), new Point(-10.0,10.0), new Point(-10.0,-10.0)));
		
		
		coordenadasCGPAlgunLugar = new Geolocalizacion(100.0, 5.0);
		posicionDeUsuario1 = new Geolocalizacion(5.0, 5.0);
		posicionDeUsuario2 = new Geolocalizacion(100.0, 100.0);
		
		
		comunaDeAlgunLugar = new Comuna(puntos);
		
		direccionDeCGPAlgunLugar = new Direccion("Calle falsa", 123);
		
		cgpAlgunLugar = new CGP("CGP1", direccionDeCGPAlgunLugar, coordenadasCGPAlgunLugar, comunaDeAlgunLugar);
		
	}
	
	
	@Test
	public void elUsuarioUnoEstaCercaDelCGP(){
		assertTrue(cgpAlgunLugar.estaCercaDe(posicionDeUsuario1));
	}
	
	@Test
	public void elUsuarioDosNoEstaCercaDelCGP(){
		assertFalse(cgpAlgunLugar.estaCercaDe(posicionDeUsuario2));
	}
	
	
	
	
	
	
	
	
	

}
