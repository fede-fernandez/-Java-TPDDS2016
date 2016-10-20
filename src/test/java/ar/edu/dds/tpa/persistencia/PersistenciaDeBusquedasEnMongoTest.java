package ar.edu.dds.tpa.persistencia;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import ar.edu.dds.tpa.datastoreTest.MorphiaDatastoreTest;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.HistorialDeBusqueda;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Usuario;

public class PersistenciaDeBusquedasEnMongoTest {
	
	private MorphiaDatastoreTest morphiaDatastore;
	private Datastore db;
	
	private Busqueda busqueda1;
	private Usuario jorge86;
	private Comuna comuna12;
	private PuntoDeInteres puntoDeInteres;
	private HistorialDeBusqueda historial;
	
	
	private LocalDate cuatroDeFebreroDe2016;
	private LocalDate diezDeEneroDe2016;

	private Busqueda busqueda2;
	private Busqueda busqueda3;
	private Busqueda busqueda4;

	private LocalComercial localComercial;
	private ParadaDeColectivo paradaDeColectivo;
	private Banco banco;
	private List<PuntoDeInteres> pisEncontrados1;
	private List<PuntoDeInteres> pisEncontrados2;
	private List<PuntoDeInteres> pisEncontrados3;

	private List<LocalDate> fechasDeBusqueda;

	
	@Before
	public void inicializar(){
		morphiaDatastore = MorphiaDatastoreTest.obtenerInstancia();
		db = morphiaDatastore.getDatastore();
		
		comuna12 = new Comuna(1, "Comuna");
		puntoDeInteres = new ParadaDeColectivo("Linea 180",new Posicion(10.0,10.0));
		jorge86 = new Usuario("Terminal", new Posicion(5.0, 5.0), comuna12);
		busqueda1 = new Busqueda(jorge86, "180", Arrays.asList(puntoDeInteres), LocalDate.now(), 5.0);
		historial = new HistorialDeBusqueda();
		historial.agregar(busqueda1);
		
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		
		fechasDeBusqueda = Arrays.asList(cuatroDeFebreroDe2016, diezDeEneroDe2016);

		localComercial = new LocalComercial();
		paradaDeColectivo = new ParadaDeColectivo();
		banco = new Banco();
		pisEncontrados1 = new ArrayList<PuntoDeInteres>();
		pisEncontrados2 = new ArrayList<PuntoDeInteres>();
		pisEncontrados3 = new ArrayList<PuntoDeInteres>();
		pisEncontrados1.add(banco);
		pisEncontrados1.add(paradaDeColectivo);
		pisEncontrados2.add(localComercial);
		pisEncontrados2.add(banco);
		pisEncontrados3.add(paradaDeColectivo);
		pisEncontrados3.add(banco);
		pisEncontrados3.add(localComercial);

		busqueda2 = new Busqueda(jorge86, "Florida", pisEncontrados1, cuatroDeFebreroDe2016, 5.0);
		busqueda3 = new Busqueda(jorge86, "Ahorro", pisEncontrados2, cuatroDeFebreroDe2016, 5.0);
		busqueda4 = new Busqueda(jorge86, "Subte", pisEncontrados3, diezDeEneroDe2016, 5.0);
	
		
	}
	
	
	@Test
	public void persistenciaPOIsEncontrados3() {
		db.save(busqueda4);
		Busqueda busquedaTest = db.find(Busqueda.class).asList().get(0);

		Assert.assertEquals(busqueda4.getPuntosDeInteresEncontrados().size(), busquedaTest.getPuntosDeInteresEncontrados().size());
		
		db.delete(busqueda4);
	}
	
	
	@Test
	public void testBasico(){
		
		db.save(busqueda1);
		
		Busqueda busquedaTest = db.find(Busqueda.class).asList().get(0);
		
		Assert.assertEquals(busqueda1.getTextoBuscado(), busquedaTest.getTextoBuscado());
		Assert.assertEquals(busqueda1.getCantidadDeResultados(), busquedaTest.getCantidadDeResultados());
		Assert.assertEquals(busqueda1.getFechaDeBusqueda(), busquedaTest.getFechaDeBusqueda());
		Assert.assertEquals(busqueda1.getUsuario().getNombre(), busquedaTest.getUsuario().getNombre());
				
		db.delete(busqueda1);
	}
	
	@Test
	public void testHistorial(){
		db.save(busqueda1);
		List<Busqueda> busquedasPersistidas = db.find(Busqueda.class).asList();
		HistorialDeBusqueda historialTest = new HistorialDeBusqueda(busquedasPersistidas);
		
		Assert.assertEquals(historial.cantidadDeBusquedasEnUnaFecha(LocalDate.now()),
				historialTest.cantidadDeBusquedasEnUnaFecha(LocalDate.now()));
		db.delete(busqueda1);
		
	}
	
	
	@Test
	public void filtradoDeFechasDeBusquedasDistintas() {

		db.save(busqueda2);
		db.save(busqueda3);
		db.save(busqueda4);
		
		List<Busqueda> busquedasPersistidas = db.find(Busqueda.class).asList();
		HistorialDeBusqueda historialTest = new HistorialDeBusqueda(busquedasPersistidas);
		Assert.assertTrue(historialTest.fechasDeBusquedas().containsAll(fechasDeBusqueda));
		
		db.delete(busqueda2);
		db.delete(busqueda3);
		db.delete(busqueda4);
	}
	
	
	@Test
	public void enCuatroDeFebreroSeRealizaronDosBusquedas() {
		
		db.save(busqueda2);
		db.save(busqueda3);
		
		List<Busqueda> busquedasPersistidas = db.find(Busqueda.class).asList();
		HistorialDeBusqueda historialTest = new HistorialDeBusqueda(busquedasPersistidas);
		Assert.assertEquals(2, historialTest.cantidadDeBusquedasEnUnaFecha(cuatroDeFebreroDe2016));
		
		db.delete(busqueda2);
		db.delete(busqueda3);
	}
	
	

}
