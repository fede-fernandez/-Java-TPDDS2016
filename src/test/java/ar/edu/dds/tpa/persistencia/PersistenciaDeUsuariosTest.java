package ar.edu.dds.tpa.persistencia;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Administrador;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.observer.NotificadorDeBusquedaLenta;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class PersistenciaDeUsuariosTest implements Persistible {

	private static Comuna flores;
	private static Comuna caballito;
	private static Comuna villaLugano;

	private static boolean seInstancio = false;;

	@Before
	public void inicializar() {
		if (!seInstancio) {
			flores = new Comuna(7, "Flores");
			caballito = new Comuna(9, "Caballito");
			villaLugano = new Comuna(14, "Villa Lugano");
			seInstancio = true;
		}
	}

	@Test
	public void persistenciaDeNombreDeUsuario() {
		Terminal terminalDePrueba1 = new Terminal("Terminal Subte Linea E Virreyes", new Posicion(2.42300, 2.04212),
				flores);
		repositorio.persistir(terminalDePrueba1);
		Assert.assertEquals(terminalDePrueba1.getNombre(),
				repositorio.buscarPorID(Terminal.class, terminalDePrueba1.getId()).getNombre());
	}

	@Test
	public void persistenciaDeComunaDeUsuario() {
		Terminal terminalDePrueba2 = new Terminal("Terminal Eva Peron y Varela", new Posicion(1.9353, 9.3493), flores);
		repositorio.persistir(terminalDePrueba2);
		Assert.assertEquals(flores.getNombre(),
				repositorio.buscarPorID(Terminal.class, terminalDePrueba2.getId()).getComuna().getNombre());
	}

	@Test
	public void persistenciaDeUsuarios() {
		Terminal terminalDePrueba3 = new Terminal("Terminal Acoyte y Rivadavia", new Posicion(15.15, 9.3848),
				caballito);
		Terminal terminalDePrueba4 = new Terminal("Terminal Central Olivera", new Posicion(8.3, 8.7), villaLugano);

		repositorio.persistir(terminalDePrueba3);
		repositorio.persistir(terminalDePrueba4);

		Assert.assertTrue(repositorio.traerTodos(Terminal.class).stream()
				.anyMatch(unUsuario -> unUsuario.getNombre().equals("Terminal Acoyte y Rivadavia")));
	}

	@Test
	public void persistenciaDeUsuarioConNotificador() {
		Administrador administrador = new Administrador("admin@admin.com");
		NotificadorDeBusquedaLenta notificadorDeBusquedaLenta = new NotificadorDeBusquedaLenta(10.0,
				new EnviadorDeMail(new MailServiceImpostor()), administrador);
		repositorio.persistir(administrador);

		Terminal terminalDePrueba5 = new Terminal("Terminal Parque Rivadavia", new Posicion(40.458742, 19.23887),
				caballito);

		terminalDePrueba5.agregarObservadorDeBusqueda(notificadorDeBusquedaLenta);
		repositorio.persistir(terminalDePrueba5);

		Terminal terminalEncontrada = repositorio.buscarPorID(Terminal.class, terminalDePrueba5.getId());
		Assert.assertEquals(terminalEncontrada.getObservadoresDeBusqueda().size(),
				terminalDePrueba5.getObservadoresDeBusqueda().size());
	}

	@AfterClass
	public static void guardarYCerrarSesion() {
		repositorio.cerrarSesion();
	}
}