package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ParadaDeColectivo;

public class PersistenciaDePuntoDeInteresTest {

	private Mapa mapa;
	private ParadaDeColectivo paradaDel7;

	@Before
	public void inicializar() {
		mapa = new Mapa();
		paradaDel7 = new ParadaDeColectivo("Estacion Retiro", new Posicion(30.005, 50.003));
	}

	@Test
	public void persistenciaDeParadaDeColectivo() {
		mapa.almacenar(paradaDel7);
		Assert.assertEquals(paradaDel7, mapa.traerPuntoDeInteresPor(paradaDel7.getId()));
	}
}