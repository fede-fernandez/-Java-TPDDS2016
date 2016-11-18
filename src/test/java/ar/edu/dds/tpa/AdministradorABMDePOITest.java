package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.historial.HistorialDeBusquedaEnMemoria;
import ar.edu.dds.tpa.model.*;
import ar.edu.dds.tpa.persistencia.MapaEnMemoria;

public class AdministradorABMDePOITest {

	private Administrador administrador;
	private MapaEnMemoria mapa;
	private ParadaDeColectivo paradaDel7;
	private Banco bancoSantanderRio;
	private Buscador buscador;

	@Before
	public void inicializar() {
		administrador = new Administrador(null);
		mapa = new MapaEnMemoria();
		paradaDel7 = new ParadaDeColectivo("parada del colectivo linea 7", null);
		bancoSantanderRio = new Banco("Banco Santander Rio Sucursal Ramos Mejia", null);
		buscador = new Buscador(mapa, new HistorialDeBusquedaEnMemoria());
	}

	@Test
	public void altaDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(bancoSantanderRio, mapa);

		Assert.assertTrue(buscador.buscar("santander", null).contains(bancoSantanderRio));
	}

	@Test
	public void bajaDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(paradaDel7, mapa);
		administrador.sacarPuntoDeInteres(paradaDel7, mapa);

		Assert.assertFalse(buscador.buscar("7", null).contains(paradaDel7));
	}

	@Test
	public void modificacionDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(paradaDel7, mapa);
		ParadaDeColectivo paradaDel7Modificada = new ParadaDeColectivo("terminal de bondi 7", null);
		administrador.modificarPuntoDeInteres(paradaDel7, paradaDel7Modificada, mapa);

		Assert.assertTrue(buscador.buscar("bondi", null).contains(paradaDel7Modificada)
				&& !buscador.buscar("colectivo", null).contains(paradaDel7));
	}
}