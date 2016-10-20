package ar.edu.dds.tpa.persistencia;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import ar.edu.dds.tpa.datastoreTest.MorphiaDatastoreTest;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Usuario;

public class PersistenciaDeBusquedasEnMongoTest {
	
	private MorphiaDatastoreTest morphiaDatastore;
	private Datastore datastore;
	private Busqueda busqueda1;
	private Usuario usuario;
	private Comuna comuna;
	private PuntoDeInteres puntoDeInteres;
	private HistorialDeBusqueda historial;
	
	@Before
	public void inicializar(){
		morphiaDatastore = MorphiaDatastoreTest.obtenerInstancia();
		datastore = morphiaDatastore.getDatastore();
		comuna = new Comuna(1, "Comuna");
		puntoDeInteres = new ParadaDeColectivo("Linea 180",new Posicion(10.0,10.0));
		usuario = new Usuario("Terminal", new Posicion(5.0, 5.0), comuna);
		busqueda1 = new Busqueda(usuario, "180", Arrays.asList(puntoDeInteres), LocalDate.now(), 5.0);
		historial = new HistorialDeBusqueda();
		historial.agregar(busqueda1);
	}
	
	@Test
	public void testBasico(){
		
		datastore.save(busqueda1);
		
		Busqueda busqueda2 = datastore.find(Busqueda.class).asList().get(0);
		
		Assert.assertEquals(busqueda1.getTextoBuscado(), busqueda2.getTextoBuscado());
		Assert.assertEquals(busqueda1.getCantidadDeResultados(), busqueda2.getCantidadDeResultados());
		Assert.assertEquals(busqueda1.getFechaDeBusqueda(), busqueda2.getFechaDeBusqueda());
		Assert.assertEquals(busqueda1.getUsuario().getNombre(), busqueda2.getUsuario().getNombre());
				
		datastore.delete(busqueda1);
	}
	
	@Test
	public void testHistorial(){
		datastore.save(busqueda1);
		
		List<Busqueda> busquedasPersistidas = datastore.find(Busqueda.class).asList();
		
		HistorialDeBusqueda historialTest = new HistorialDeBusqueda(busquedasPersistidas);
		
		Assert.assertEquals(historial.cantidadDeBusquedasEnUnaFecha(LocalDate.now()), historialTest.cantidadDeBusquedasEnUnaFecha(LocalDate.now()));
		
		datastore.delete(busqueda1);
		
	}
	
	

}
