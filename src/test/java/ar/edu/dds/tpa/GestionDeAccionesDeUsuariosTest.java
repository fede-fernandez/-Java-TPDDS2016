package ar.edu.dds.tpa;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.accion.DesactivadorDeNotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.criterio.FiltradoPorComuna;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.procesos.GestorDeAccionesDeUsuarios;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class GestionDeAccionesDeUsuariosTest {

	private GestorDeAccionesDeUsuarios proceso3;
	private FiltradoPorComuna filtroPorComuna;
	private DesactivadorDeNotificadorDeBusquedaLenta desactivarNotificadorDeBusqueda;
	private NotificadorDeBusquedaLenta notificadorDeBusquedaLenta;
	private EnviadorDeMail enviadorDeMail;
	private MailServiceImpostor mailServiceImpostor;
	private Administrador administrador;
	private Usuario usuarioPepe;
	private List<Usuario> usuarios;
	private Mapa mapa;
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
		usuarioPepe = new Usuario("Pepe", new Posicion(5.0, 10.0), comunaDeFlores);
		usuarios = new ArrayList<Usuario>();
		usuarios.add(usuarioPepe);
		mapa = new Mapa();
		buscador = new Buscador(mapa);

		proceso3 = new GestorDeAccionesDeUsuarios(usuarios, filtroPorComuna, desactivarNotificadorDeBusqueda);
	}

	@Test
	public void seEjecutaElProcesoYNoSeEnviaMailAlAdministrador() {
		proceso3.ejecutar();
		buscador.registrarBusqueda(usuarioPepe, null, 0, LocalDateTime.now(),
				LocalDateTime.now().plus(Duration.ofHours(5)));
		Assert.assertFalse(mailServiceImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
}