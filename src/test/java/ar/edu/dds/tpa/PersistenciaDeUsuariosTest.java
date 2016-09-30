package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.GestorDeAdministradores;
import ar.edu.dds.tpa.model.GestorDeUsuarios;
import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class PersistenciaDeUsuariosTest {

	private GestorDeUsuarios gestorDeUsuarios;
	private GestorDeAdministradores gestorDeAdministradores;
	private Usuario usuarioDePrueba1;
	private Comuna flores = new Comuna(7, "Flores");
	private Comuna caballito = new Comuna(9, "Caballito");
	private Usuario usuarioDePrueba2;
	private Usuario usuarioDePrueba3;
	private NotificadorDeBusquedaLenta notificadorDeBusquedaLenta;
	private Administrador administrador;

	@Before
	public void inicializar() {
		gestorDeUsuarios = new GestorDeUsuarios();
		gestorDeAdministradores = new GestorDeAdministradores();
		usuarioDePrueba1 = new Usuario("Carlos", new Posicion(2.0, 2.0), flores);
		usuarioDePrueba2 = new Usuario("Matias", new Posicion(5.0, 5.0), caballito);
		usuarioDePrueba3 = new Usuario("Luis", new Posicion(7.0, 7.0), caballito);
		administrador = new Administrador("admin@admin.com");
		notificadorDeBusquedaLenta = new NotificadorDeBusquedaLenta(10.0, new EnviadorDeMail(new MailServiceImpostor()), administrador);
	}

	@Test
	public void persistenciaDeNombreDeUsuario() {
		gestorDeUsuarios.registrar(usuarioDePrueba1);
		Assert.assertEquals(usuarioDePrueba1.getNombre(),
				gestorDeUsuarios.traerUsuarioPor(usuarioDePrueba1.getId()).getNombre());
	}

	@Test
	public void persistenciaDeComunaDeUsuario() {
		gestorDeUsuarios.registrar(usuarioDePrueba1);
		Assert.assertEquals(flores.getNombre(),
				gestorDeUsuarios.traerUsuarioPor(usuarioDePrueba1.getId()).getComuna().getNombre());
	}

	@Test
	public void persistenciaDeUsuarios() {
		gestorDeUsuarios.registrar(usuarioDePrueba1);
		gestorDeUsuarios.registrar(usuarioDePrueba2);
		Assert.assertTrue(gestorDeUsuarios.traerUsuarios().stream()
				.anyMatch(unUsuario -> unUsuario.getNombre().equals("Matias")));
	}
	
	@Test
	public void persistenciaDeUsuarioConNotificador(){
		gestorDeAdministradores.registrar(administrador);		
		usuarioDePrueba3.agregarObservadorDeBusqueda(notificadorDeBusquedaLenta);
		gestorDeUsuarios.registrar(usuarioDePrueba3);
		
		Usuario usuario = gestorDeUsuarios.traerUsuarioPor(usuarioDePrueba3.getId());
		Assert.assertEquals(usuario.getObservadoresDeBusqueda().size(),usuarioDePrueba3.getObservadoresDeBusqueda().size());
	}
}