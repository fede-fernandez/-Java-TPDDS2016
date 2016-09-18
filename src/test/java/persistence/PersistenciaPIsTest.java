package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.edu.dds.tpa.dataBase.BaseDeDatos;
import ar.edu.dds.tpa.geolocalizacion.Poligono;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Rubro;



public class PersistenciaPIsTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	  private BaseDeDatos db;

	  @Before
	  public void setUp() {
		  db = new BaseDeDatos();
	  }
	
	  @Test
	  public void persisteParadaDeColectivo() {
	   ParadaDeColectivo bondi114 = new ParadaDeColectivo("114", new Posicion(200.0006, 100.0));
	   db.guardar(bondi114);
	   assertEquals(db.obtenerPuntoInteres(bondi114.getID()), bondi114);
	   
	  }
	  
	  @Test
	  public void persisteCGP() {
		  
	  CGP cgpDeFlores = new CGP("CGPFlores", new Posicion(100.0, 5.0));
	  List<Posicion> puntos = new ArrayList<Posicion>(Arrays.asList(new Posicion(10.0, 10.0), new Posicion(10.0, -10.0),
				new Posicion(-10.0, 10.0), new Posicion(-10.0, -10.0)));
	  Poligono barrioDeFlores = new Poligono(puntos);
	  cgpDeFlores.agregarZonaDeCobertura(barrioDeFlores);
	  db.guardar(cgpDeFlores);
	  assertEquals(db.obtenerPuntoInteres(cgpDeFlores.getID()), cgpDeFlores);
	   
	  }
	  
	  
	  @Test
	  public void persisteBanco() {
		  
	  Banco bancoPatagonia = new Banco("Banco Patagonia", new Posicion(100.000004, 50.0));
	  db.guardar(bancoPatagonia);
	  assertEquals(db.obtenerPuntoInteres(bancoPatagonia.getID()), bancoPatagonia);
	   
	  }
	  
	  
	  @Test
	  public void persisteLocalComercial() {
		  
	  LocalComercial unLocalDeDiarios1 = new LocalComercial("Diarin", new Posicion(100.000002, 50.0),
					new Rubro("Kiosco de diarios", 2.00));
	   db.guardar(unLocalDeDiarios1);
	   assertEquals(db.obtenerPuntoInteres(unLocalDeDiarios1.getID()), unLocalDeDiarios1);
	   
	  }
	  
	  
	  @Test
	  public void persisteDeUnaListaDePOIs() {
	  ParadaDeColectivo bondi114 = new ParadaDeColectivo("114", new Posicion(124.0006, 33.0));
	  db.guardar(bondi114);
	  
	  ParadaDeColectivo bondi7 = new ParadaDeColectivo("7", new Posicion(200.0006, 100.0));
	  db.guardar(bondi7);
	  
	  Assert.assertEquals(2, db.todosPOIs().size());
	  Assert.assertEquals("7", db.todosPOIs().get(1).getNombre());
	  }
	 
	  
	
}
