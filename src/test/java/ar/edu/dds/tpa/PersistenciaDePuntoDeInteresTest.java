package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Usuario;

public class PersistenciaDePuntoDeInteresTest {

	private Mapa mapa;
	private ParadaDeColectivo paradaDel7;
	private LocalComercial localDeDiarios;
	private Rubro kioscoDeDiarios;
	private Usuario terminalFlores;

	@Before
	public void inicializar() {
		mapa = new Mapa();
		paradaDel7 = new ParadaDeColectivo("Estacion Retiro", new Posicion(5.000005, 7.000007));
		kioscoDeDiarios = new Rubro("Kiosco de Diarios", 10.5);
		localDeDiarios = new LocalComercial("Diarin", new Posicion(5.000004, 7.000002), kioscoDeDiarios);
		terminalFlores = new Usuario("Terminal de Flores", new Posicion(5.000003, 7.000005), null);
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
	public void  persistenciaDeVariosPuntosDeInteresContieneUnaParadaDeColectivo() {
		mapa.almacenar(paradaDel7);
		mapa.almacenar(localDeDiarios);
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream().anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("RETIRO")));
		Assert.assertTrue(mapa.traerPuntosDeInteres().stream().anyMatch(unPuntoDeInteres -> unPuntoDeInteres.contienePalabraClave("DIARIN")));
	}
}