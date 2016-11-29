package ar.edu.dds.tpa;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.model.*;
import ar.edu.dds.tpa.model.usuario.Administrador;
import ar.edu.dds.tpa.model.usuario.Terminal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.accion.DesactivadorDeNotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.criterio.FiltradoPorComuna;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.historial.HistorialDeBusquedaEnMemoria;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.persistencia.repository.MapaEnMemoria;
import ar.edu.dds.tpa.procesos.GestorDeAccionesDeTerminales;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class GestionDeAccionesDeTerminalesTest {

	private GestorDeAccionesDeTerminales proceso3;
	private FiltradoPorComuna filtroPorComuna;
	private DesactivadorDeNotificadorDeBusquedaLenta desactivarNotificadorDeBusqueda;
	private NotificadorDeBusquedaLenta notificadorDeBusquedaLenta;
	private EnviadorDeMail enviadorDeMail;
	private MailServiceImpostor mailServiceImpostor;
	private Administrador administrador;
	private Terminal terminal;
	private List<Terminal> terminales;
	private MapaEnMemoria mapa;
	private Buscador buscador;
	private Comuna comunaDeFlores;

	@Before
	public void inicializar() {
		mailServiceImpostor = new MailServiceImpostor();
		enviadorDeMail = new EnviadorDeMail(mailServiceImpostor);
		administrador = new Administrador("admin@admin.com");
		notificadorDeBusquedaLenta = new NotificadorDeBusquedaLenta(5.0, enviadorDeMail, administrador);
		desactivarNotificadorDeBusqueda = new DesactivadorDeNotificadorDeBusquedaLenta(notificadorDeBusquedaLenta);
		comunaDeFlores = new Comuna(7, "Flores");
		filtroPorComuna = new FiltradoPorComuna(comunaDeFlores);
		terminal = new Terminal("Terminal Flores", new Posicion(5.0, 10.0), comunaDeFlores);
		terminales = new ArrayList<Terminal>();
		terminales.add(terminal);
		mapa = new MapaEnMemoria();
		buscador = new Buscador(mapa, new HistorialDeBusquedaEnMemoria());

		proceso3 = new GestorDeAccionesDeTerminales(terminales, filtroPorComuna, desactivarNotificadorDeBusqueda);
	}

	@Test
	public void seEjecutaElProcesoYNoSeEnviaMailAlAdministrador() {
		proceso3.ejecutar();
		buscador.registrarBusqueda(terminal, null, new ArrayList<PuntoDeInteres>(), LocalDateTime.now(),
				LocalDateTime.now().plus(Duration.ofHours(5)));
		Assert.assertFalse(mailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
}