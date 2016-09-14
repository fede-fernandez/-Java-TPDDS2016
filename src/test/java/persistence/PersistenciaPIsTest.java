package persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.edu.dds.tpa.dataBase.BaseDeDatos;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.ParadaDeColectivo;



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
	  
	
}
