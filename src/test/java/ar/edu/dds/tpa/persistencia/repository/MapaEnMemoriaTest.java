package ar.edu.dds.tpa.persistencia.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.persistencia.repository.historial.HistorialDeBusquedaEnMemoria;

public class MapaEnMemoriaTest {

	private MapaEnMemoria mapa;
	private ParadaDeColectivo paradaDel7;
	private Banco bancoSantanderRio;
	private Buscador buscador;

	@Before
	public void inicializar() {
		mapa = new MapaEnMemoria();
		paradaDel7 = new ParadaDeColectivo("parada del colectivo linea 7", null, null);
		bancoSantanderRio = new Banco("Banco Santander Rio Sucursal Ramos Mejia", null, null);
		buscador = new Buscador(mapa, new HistorialDeBusquedaEnMemoria());
	}

	@Test
	public void altaDePuntoDeInteres() {
		mapa.agregar(bancoSantanderRio);

		Assert.assertTrue(buscador.buscar("santander", null).contains(bancoSantanderRio));
	}

	@Test
	public void bajaDePuntoDeInteres() {
		mapa.agregar(paradaDel7);
		mapa.remover(paradaDel7);

		Assert.assertFalse(buscador.buscar("7", null).contains(paradaDel7));
	}

	@Test
	public void modificacionDePuntoDeInteres() {

		mapa.agregar(paradaDel7);
		paradaDel7.setNombre("terminal de bondi 7");

		Assert.assertTrue(buscador.buscar("bondi", null).contains(paradaDel7)
				&& !buscador.buscar("colectivo", null).contains(paradaDel7));
	}
}