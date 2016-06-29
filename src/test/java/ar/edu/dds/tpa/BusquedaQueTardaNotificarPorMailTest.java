package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.BusquedaRealizada;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class BusquedaQueTardaNotificarPorMailTest {
	Administrador administrador;
	Mapa mapa;
	Terminal terminalFlores;
	NotificadorDeBusquedaLenta notificadorDeBusquedaLenta;
	MailServiceImpostor envioDeMailServiceImpostor;

	@Before
	public void inicializar() {
		administrador = new Administrador(null);
		mapa = new Mapa();
		administrador = new Administrador("elAdminDelSistema@puntosdeinteres.com");
		terminalFlores = new Terminal(null, null);
		administrador.agregarTerminal(terminalFlores);
		administrador.agregarMapaATerminales();
		envioDeMailServiceImpostor = new MailServiceImpostor();
		notificadorDeBusquedaLenta = new NotificadorDeBusquedaLenta(60, envioDeMailServiceImpostor, administrador);
		terminalFlores.registrarObserverDeBusqueda(notificadorDeBusquedaLenta);
	}
	
	@Test
	public void seNotificaAlAdministradorUnaBusquedaLenta() {
		BusquedaRealizada busquedaRealizada = new BusquedaRealizada(null, 0, null, 180);
		terminalFlores.agregarBusquedaRealizada(busquedaRealizada);
		Assert.assertTrue(envioDeMailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
	
	@Test
	public void noSeNotificaAlAdministradorUnaBusquedaRapida() {
		BusquedaRealizada busquedaRealizada = new BusquedaRealizada(null, 0, null, 15);
		terminalFlores.agregarBusquedaRealizada(busquedaRealizada);
		Assert.assertFalse(envioDeMailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
}
