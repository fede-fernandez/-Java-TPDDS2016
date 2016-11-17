package ar.edu.dds.tpa;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.edu.dds.tpa.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.persistencia.MapaEnMemoria;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class BusquedaQueTardaNotificarPorMailTest {

	private Administrador administrador;
	private MapaEnMemoria mapa;
	private Buscador buscador;
	private NotificadorDeBusquedaLenta notificadorDeBusquedaLenta;
	private EnviadorDeMail enviadorDeMail;
	private MailServiceImpostor envioDeMailServiceImpostor;
	private Terminal terminal;

	@Before
	public void inicializar() {
		mapa = new MapaEnMemoria();
		administrador = new Administrador("elAdminDelSistema@puntosdeinteres.com");
		envioDeMailServiceImpostor = new MailServiceImpostor();
		enviadorDeMail = new EnviadorDeMail(envioDeMailServiceImpostor);
		notificadorDeBusquedaLenta = new NotificadorDeBusquedaLenta(60, enviadorDeMail, administrador);
		buscador = new Buscador(mapa);
		terminal = new Terminal("Terminal", new Posicion(5.0, 6.0), null);
		terminal.agregarObservadorDeBusqueda(notificadorDeBusquedaLenta);
	}

	@Test
	public void seNotificaAlAdministradorUnaBusquedaLenta() {
		buscador.registrarBusqueda(terminal, null, new ArrayList<PuntoDeInteres>(), LocalDateTime.now(),
				LocalDateTime.now().plus(Duration.ofHours(1)));
		Assert.assertTrue(envioDeMailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}

	@Test
	public void noSeNotificaAlAdministradorUnaBusquedaRapida() {
		buscador.registrarBusqueda(terminal, null, new ArrayList<PuntoDeInteres>(), LocalDateTime.now(),
				LocalDateTime.now());
		Assert.assertFalse(envioDeMailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
}