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
	private LocalDate cincoDeSeptiembreDe2014;

	private Busqueda busqueda2;
	private Busqueda busqueda3;
	private Busqueda busqueda4;
	private Busqueda busqueda5;

	private LocalComercial localComercial;
	private ParadaDeColectivo paradaDeColectivo;
	private Banco banco;
	private List<PuntoDeInteres> poisEncontrados1;
	private List<PuntoDeInteres> poisEncontrados2;
	private List<PuntoDeInteres> poisEncontrados3;
	private List<PuntoDeInteres> poisEncontrados4;

	private List<LocalDate> fechasDeBusqueda;
	
	private RepositorioDeBusquedas repositorio;

	
	@Before
	public void inicializar(){
		morphiaDatastore = MorphiaDatastoreTest.obtenerInstancia();
		db = morphiaDatastore.getDatastore();
		repositorio = new RepositorioDeBusquedas(db);
		
		comuna12 = new Comuna(1, "Comuna");
		puntoDeInteres = new ParadaDeColectivo("Linea 180",new Posicion(10.0,10.0));
		jorge86 = new Usuario("Terminal", new Posicion(5.0, 5.0), comuna12);
		busqueda1 = new Busqueda(jorge86, "180", Arrays.asList(puntoDeInteres), LocalDate.now(), 5.0);
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		cincoDeSeptiembreDe2014 = LocalDate.of(2014, Month.SEPTEMBER, 5);
		
		
		fechasDeBusqueda = Arrays.asList(cuatroDeFebreroDe2016, diezDeEneroDe2016);

		localComercial = new LocalComercial();
		paradaDeColectivo = new ParadaDeColectivo();
		banco = new Banco();
		poisEncontrados1 = new ArrayList<PuntoDeInteres>();
		poisEncontrados2 = new ArrayList<PuntoDeInteres>();
		poisEncontrados3 = new ArrayList<PuntoDeInteres>();
		poisEncontrados1.add(banco);
		poisEncontrados1.add(paradaDeColectivo);
		poisEncontrados2.add(localComercial);
		poisEncontrados2.add(banco);
		poisEncontrados3.add(paradaDeColectivo);
		poisEncontrados3.add(banco);
		poisEncontrados3.add(localComercial);
		poisEncontrados4 = Arrays.asList(banco,paradaDeColectivo);

		busqueda2 = new Busqueda(jorge86, "Florida", poisEncontrados1, cuatroDeFebreroDe2016, 5.0);
		busqueda3 = new Busqueda(jorge86, "Ahorro", poisEncontrados2, cuatroDeFebreroDe2016, 5.0);
		busqueda4 = new Busqueda(jorge86, "Subte", poisEncontrados3, diezDeEneroDe2016, 5.0);
		busqueda5 = new Busqueda(jorge86, "Florida",poisEncontrados4,cincoDeSeptiembreDe2014,10.0);
	
		
	}
	
	@Test
	public void buscarPOIsEntreDosFechas(){
		
		repositorio.guardar(busqueda2);
		repositorio.guardar(busqueda3);
		repositorio.guardar(busqueda4);
		
		HistorialDeBusqueda historial = repositorio.encontrarLasBusquedasEntreDosFechas(diezDeEneroDe2016, cuatroDeFebreroDe2016);
		
		Assert.assertEquals(3, historial.cantidadDeBusquedasRealizadas());
		
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
	}
	
	@Test
	public void buscarPOIsPorTextoBuscado(){
		repositorio.guardar(busqueda1);
		repositorio.guardar(busqueda2);
		repositorio.guardar(busqueda3);
		repositorio.guardar(busqueda4);
		repositorio.guardar(busqueda5);
		
		HistorialDeBusqueda historial = repositorio.encontrarTodasLasBusquedasPorTextoBuscado("Florida");
		
		Assert.assertEquals(2, historial.cantidadDeBusquedasRealizadas());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
		repositorio.eliminarBusqueda(busqueda5);
	}
	
	@Test
	public void traerTodasLasBusquedas() {
		repositorio.guardar(busqueda4);
		HistorialDeBusqueda historial = repositorio.traerTodasLasBusquedas();
		
		Assert.assertEquals(historial.cantidadDeBusquedasRealizadas(), 1);		
		
		repositorio.eliminarBusqueda(busqueda4);	
	}
	
	@Test
	public void buscarPOIsPorTextoYEntreDosFechasSimultaneamente(){
		repositorio.guardar(busqueda1);
		repositorio.guardar(busqueda2);
		repositorio.guardar(busqueda3);
		repositorio.guardar(busqueda4);
		repositorio.guardar(busqueda5);
		
		HistorialDeBusqueda historial = repositorio.encontrarLasBusquedasEntreDosFechasYPorTextoBuscado("Florida", diezDeEneroDe2016, cuatroDeFebreroDe2016);
		
		Assert.assertEquals(1, historial.cantidadDeBusquedasRealizadas());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
		repositorio.eliminarBusqueda(busqueda5);
	}
	
	@Test
	public void enCuatroDeFebreroSeRealizaronDosBusquedas() {
		repositorio.guardar(busqueda2);
		repositorio.guardar(busqueda3);
	
		HistorialDeBusqueda historial = repositorio.encontrarLasBusquedasQueSeRealizaronEnUnaFecha(cuatroDeFebreroDe2016);
		
		Assert.assertEquals(2, historial.cantidadDeBusquedasEnUnaFecha(cuatroDeFebreroDe2016));
		
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		
	}
}
