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

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.PuntosDeInteresEncontrados;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.builder.ConsultaBusquedaBuilder;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaBusqueda;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorFecha;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorRangoDeFechas;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorTerminal;
import ar.edu.dds.tpa.persistencia.consulta.ConsultaPorTexto;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusquedaEnMongo;

public class PersistenciaDeBusquedasEnMongoTest implements MorphiaDatastore{
	
	private MorphiaDatastoreMock morphiaDatastore;
	private Datastore db;
	
	private Busqueda busqueda1;
	private Terminal terminalChacabuco;
	private Comuna comuna12;
	private PuntoDeInteres puntoDeInteres;
		
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

	private HistorialDeBusquedaEnMongo repositorio;
	
	private ConsultaBusqueda consultaPorTextoBuscado;
	private ConsultaBusqueda consultaPorRangoDeFechas;	
	private ConsultaBusqueda consultaPorFecha;
	private ConsultaBusqueda consultaPorTerminal;
	
	private ConsultaBusquedaBuilder builderDeConsulta;

	
	@Before
	public void inicializar(){
		morphiaDatastore = MorphiaDatastoreMock.obtenerInstancia();
		db = morphiaDatastore.getDatastore();
		repositorio = new HistorialDeBusquedaEnMongo(db);
		
		comuna12 = new Comuna(1, "Comuna");
		puntoDeInteres = new ParadaDeColectivo("Linea 180",new Posicion(10.0,10.0),null);
		terminalChacabuco = new Terminal("Terminal Chacabuco", new Posicion(5.0, 5.0), comuna12);
		busqueda1 = new Busqueda(terminalChacabuco, "180", new PuntosDeInteresEncontrados(Arrays.asList(puntoDeInteres)), LocalDate.now(), 5.0);
		
		cuatroDeFebreroDe2016 = LocalDate.of(2016, Month.FEBRUARY, 4);
		diezDeEneroDe2016 = LocalDate.of(2016, Month.JANUARY, 10);
		cincoDeSeptiembreDe2014 = LocalDate.of(2014, Month.SEPTEMBER, 5);
		
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

		
		busqueda2 = new Busqueda(terminalChacabuco, "Florida", new PuntosDeInteresEncontrados(poisEncontrados1), cuatroDeFebreroDe2016, 5.0);
		busqueda3 = new Busqueda(terminalChacabuco, "Ahorro",  new PuntosDeInteresEncontrados(poisEncontrados2), cuatroDeFebreroDe2016, 5.0);
		busqueda4 = new Busqueda(terminalChacabuco, "Subte",  new PuntosDeInteresEncontrados(poisEncontrados3), diezDeEneroDe2016, 5.0);
		busqueda5 = new Busqueda(terminalChacabuco, "Florida", new PuntosDeInteresEncontrados(poisEncontrados4),cincoDeSeptiembreDe2014,10.0);
				
		builderDeConsulta = new ConsultaBusquedaBuilder();
	
		
	}
	
	@Test
	public void buscarPOIsEntreDosFechas(){
		
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);
		
		consultaPorRangoDeFechas = new ConsultaPorRangoDeFechas(diezDeEneroDe2016, cuatroDeFebreroDe2016);
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorRangoDeFechas));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(3, historial.size());
		
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
	}
	
	@Test
	public void buscarPOIsPorTextoBuscado(){
		repositorio.registrarBusqueda(busqueda1);
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);
		repositorio.registrarBusqueda(busqueda5);
				
		consultaPorTextoBuscado = new ConsultaPorTexto("Florida");
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorTextoBuscado));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(2, historial.size());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
		repositorio.eliminarBusqueda(busqueda5);
	}
	
	@Test
	public void traerTodasLasBusquedas() {
		repositorio.registrarBusqueda(busqueda4);
		List<Busqueda> historial = repositorio.traerTodasLasBusquedas();
		
		Assert.assertEquals(historial.size(), 1);		
		
		repositorio.eliminarBusqueda(busqueda4);	
	}
	
	@Test
	public void buscarPOIsPorTextoYEntreDosFechasSimultaneamente(){
		repositorio.registrarBusqueda(busqueda1);
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);
		repositorio.registrarBusqueda(busqueda5);
		
		consultaPorTextoBuscado = new ConsultaPorTexto("Florida");
		consultaPorRangoDeFechas = new ConsultaPorRangoDeFechas(diezDeEneroDe2016, cuatroDeFebreroDe2016);
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorTextoBuscado,consultaPorRangoDeFechas));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(1, historial.size());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
		repositorio.eliminarBusqueda(busqueda5);
	}
	
	@Test
	public void enCuatroDeFebreroSeRealizaronDosBusquedas() {
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
			
		consultaPorFecha = new ConsultaPorFecha(cuatroDeFebreroDe2016);
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorFecha));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(2, historial.size());
		
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		
	}
	
	@Test
	public void buscarPorUnaFechaEnLaQueNoHuboBusquedas(){
		repositorio.registrarBusqueda(busqueda1);
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);
				
		consultaPorFecha = new ConsultaPorFecha(LocalDate.of(2000, 1, 1));
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorFecha));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(0, historial.size());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
		
	}
	
	@Test
	public void buscarPorTextoPorElQueNoSeBusco(){
		repositorio.registrarBusqueda(busqueda1);
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);
		
		consultaPorTextoBuscado = new ConsultaPorTexto("Texto nunca buscado");
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorTextoBuscado));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(0, historial.size());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
	}
	
	@Test
	public void buscarEnUnaFechaQueSeBuscoConTextoIncorrecto(){
		
		repositorio.registrarBusqueda(busqueda1);
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);

		consultaPorTextoBuscado = new ConsultaPorTexto("Texto nunca buscado");
		consultaPorRangoDeFechas = new ConsultaPorRangoDeFechas(diezDeEneroDe2016, cuatroDeFebreroDe2016);
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorTextoBuscado,consultaPorRangoDeFechas));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(0, historial.size());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
	}
	
	@Test
	public void buscarPorTextoEnUnRangoDeFechasEnElQueNoSeBuscoConEseTexto(){
		repositorio.registrarBusqueda(busqueda1);
		repositorio.registrarBusqueda(busqueda2);
		repositorio.registrarBusqueda(busqueda3);
		repositorio.registrarBusqueda(busqueda4);
		repositorio.registrarBusqueda(busqueda5);
		
		consultaPorTextoBuscado = new ConsultaPorTexto("Florida");
		consultaPorRangoDeFechas = new ConsultaPorRangoDeFechas(LocalDate.of(2000, 1, 1), LocalDate.of(2000, 2, 1));
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorTextoBuscado,consultaPorRangoDeFechas));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(0, historial.size());
		
		repositorio.eliminarBusqueda(busqueda1);
		repositorio.eliminarBusqueda(busqueda2);
		repositorio.eliminarBusqueda(busqueda3);
		repositorio.eliminarBusqueda(busqueda4);
		repositorio.eliminarBusqueda(busqueda5);
	}
	
	@Test
	public void encontrarLasBusquedasDeUnaTerminal(){
		repositorio.registrarBusqueda(busqueda1);
		
		consultaPorTerminal = new ConsultaPorTerminal("Terminal Chacabuco");
		builderDeConsulta.setConsultas(Arrays.asList(consultaPorTerminal));
		
		List<Busqueda> historial = repositorio.ejecutarConsulta(builderDeConsulta.construirConsulta());
		
		Assert.assertEquals(1, historial.size());
	}
	
	@Test
	public void encontrarBusquedaPorId(){
		repositorio.registrarBusqueda(busqueda1);
		
		Busqueda busqueda = repositorio.encontrarBusquedaPorId(busqueda1.getId());
		
		Assert.assertEquals(busqueda.getId(), busqueda1.getId());
	}
		
}
