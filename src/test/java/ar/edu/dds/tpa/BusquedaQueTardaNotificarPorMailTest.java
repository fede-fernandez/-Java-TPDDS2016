package ar.edu.dds.tpa;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class BusquedaQueTardaNotificarPorMailTest {
	Administrador administrador;
	Mapa mapa;
	Buscador buscador;
	NotificadorDeBusquedaLenta notificadorDeBusquedaLenta;
	EnviadorDeMail enviadorDeMail;
	MailServiceImpostor envioDeMailServiceImpostor;

	@Before
	public void inicializar() {
		mapa = new Mapa();
		administrador = new Administrador("elAdminDelSistema@puntosdeinteres.com");
		envioDeMailServiceImpostor = new MailServiceImpostor();
		enviadorDeMail = new EnviadorDeMail(envioDeMailServiceImpostor);
		notificadorDeBusquedaLenta = new NotificadorDeBusquedaLenta(60, enviadorDeMail, administrador);
		buscador = new Buscador(mapa);
		buscador.agregarObservadorDeBusqueda(notificadorDeBusquedaLenta);
	}
	
	@Test
	public void seNotificaAlAdministradorUnaBusquedaLenta() {
		buscador.registrarBusqueda(null, null, 0, LocalDateTime.now(), LocalDateTime.now().plus(Duration.ofHours(1)));
		
		Assert.assertTrue(envioDeMailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
	
	@Test
	public void noSeNotificaAlAdministradorUnaBusquedaRapida() {
		buscador.registrarBusqueda(null, null, 0, LocalDateTime.now(), LocalDateTime.now());
		
		Assert.assertFalse(envioDeMailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
}