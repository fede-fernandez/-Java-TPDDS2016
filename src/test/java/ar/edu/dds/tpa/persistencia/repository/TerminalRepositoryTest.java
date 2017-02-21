package ar.edu.dds.tpa.persistencia.repository;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Repositorio;

public class TerminalRepositoryTest {

	private TerminalRepository repositorioDeTerminales;

	private Terminal terminalDePrueba1;
	private Terminal terminalDePrueba2;

	@Before
	public void inicializar() {
		repositorioDeTerminales = new TerminalRepository();
		terminalDePrueba1 = new Terminal("Terminal Liniers", new Posicion(20.0, 94.3), new Comuna(44, "Liniers"));
		terminalDePrueba2 = new Terminal("Terminal Mayo", new Posicion(34.23, 53.34), new Comuna(55, "Ramos Mejía"));
	}

	@Test
	public void buscarTerminalPorIdTest() {
		repositorioDeTerminales.agregarTerminal(terminalDePrueba1);
		Assert.assertEquals(repositorioDeTerminales.obtenerTerminalPor(terminalDePrueba1.getId()), terminalDePrueba1);
	}

	@Test
	public void buscarTerminalPorComunaTest() {
		repositorioDeTerminales.agregarTerminal(terminalDePrueba2);
		Assert.assertEquals(repositorioDeTerminales.obtenerTerminalesPor(55).get(0), terminalDePrueba2);
	}
	
	@Test
	public void buscarTerminales() {
		Assert.assertEquals(repositorioDeTerminales.obtenerTerminales().size(), 2);
	}

	@AfterClass
	public static void guardarYCerrarSesion() {
		Repositorio.obtenerRepositorioANivelThread().cerrarSesion();
	}

}