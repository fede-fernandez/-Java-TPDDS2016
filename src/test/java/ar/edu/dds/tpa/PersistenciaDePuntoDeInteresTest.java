package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.*;

import java.time.*;

public class PersistenciaDePuntoDeInteresTest {

	private Mapa mapa;
	private ParadaDeColectivo paradaDel7;
	private LocalComercial localDeDiarios;
	private Banco bancoSantander;
	private Rubro kioscoDeDiarios;
	private Usuario terminalFlores;
	private Servicio depositos;

	@Before
	public void inicializar() {
		mapa = new Mapa();
		paradaDel7 = new ParadaDeColectivo("Estacion Retiro", new Posicion(5.000005, 7.000007));
		kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		localDeDiarios = new LocalComercial("Diarin", new Posicion(5.000004, 7.000002), kioscoDeDiarios);
		terminalFlores = new Usuario("Terminal de Flores", new Posicion(5.000003, 7.000005), null);
		bancoSantander = new Banco("Banco Santander Rio Sucursal La Plata", new Posicion(2.000005, 1.000005));
		depositos = new Servicio("depositos");
		depositos.agregarHorarioDeAtencion(DayOfWeek.MONDAY, LocalTime.of(19, 20), LocalTime.of(22, 30));
		bancoSantander.agregarServicio(depositos);
	}

	@Test
	public void persistenciaDeParadaDeColectivo() {
		mapa.almacenar(paradaDel7);
		Assert.assertEquals(paradaDel7, mapa.traerPuntoDeInteresPor(paradaDel7.getId()));
	}
	
	@Test
	public void elUsuarioEstaCercaDeLaParadaDeColectivosPersistida() {
		mapa.almacenar(paradaDel7);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(paradaDel7.getId()).estaCercaDe(terminalFlores.getCoordenadas()));
	}
	
	@Test
	public void laParadaDeColectivosPersistidaTienePalabraClave() {
		mapa.almacenar(paradaDel7);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(paradaDel7.getId()).contienePalabraClave("RETIRO"));
	}
	
	@Test
	public void persistenciaDeLocalDeDiarios() {
		mapa.almacenar(localDeDiarios);
		Assert.assertEquals(localDeDiarios, mapa.traerPuntoDeInteresPor(localDeDiarios.getId()));
	}
	
	@Test
	public void persistenciaDeLocalDeDiariosTieneRubro() {
		mapa.almacenar(localDeDiarios);
		Assert.assertEquals(kioscoDeDiarios.getNombre(), ((LocalComercial)mapa.traerPuntoDeInteresPor(localDeDiarios.getId())).getRubro().getNombre());
	}
	
	@Test
	public void elPuestoDeDiariosPersistidoContieneSuRubroComoPalabraClave() {
		mapa.almacenar(localDeDiarios);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(localDeDiarios.getId()).contienePalabraClave("kiosco"));
	}
	
	@Test
	public void elUsuarioEstaCercaDelPuestoDeDiariosPersistido() {
		mapa.almacenar(localDeDiarios);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(localDeDiarios.getId()).estaCercaDe(terminalFlores.getCoordenadas()));
	}

	@Test
	public void  persistenciaDeVariosPuntosDeInteresContieneLaParadaDeColectivoYElPuestoDeDiarios() {
		mapa.almacenar(paradaDel7);
		mapa.almacenar(localDeDiarios);
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream().anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("RETIRO")));
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream().anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("DIARIN")));
	}

	@Test
	public void bancoPersistidoAtiendeEnHorarioBancario() {
		mapa.almacenar(bancoSantander);
		Assert.assertTrue(mapa.traerPuntoDeInteresPor(bancoSantander.getId()).estaDisponibleEn(LocalDateTime.of(2016, 4, 18, 12, 30, 30)));
	}
}