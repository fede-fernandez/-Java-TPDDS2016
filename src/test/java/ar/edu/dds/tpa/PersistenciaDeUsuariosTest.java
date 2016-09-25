package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.GestorDeUsuarios;
import ar.edu.dds.tpa.model.Usuario;

public class PersistenciaDeUsuariosTest {

	private GestorDeUsuarios gestorDeUsuarios;
	private Usuario usuarioDePrueba1;
	private Comuna flores = new Comuna(7, "Flores");
	private Comuna caballito = new Comuna(9, "Caballito");
	private Usuario usuarioDePrueba2;

	@Before
	public void inicializar() {
		gestorDeUsuarios = new GestorDeUsuarios();
		usuarioDePrueba1 = new Usuario("Carlos", new Posicion(2.0, 2.0), flores);
		usuarioDePrueba2 = new Usuario("Matias", new Posicion(5.0, 5.0), caballito);
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
}