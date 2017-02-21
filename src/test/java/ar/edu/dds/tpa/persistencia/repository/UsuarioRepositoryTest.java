package ar.edu.dds.tpa.persistencia.repository;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.usuario.Administrador;
import ar.edu.dds.tpa.persistencia.Repositorio;

public class UsuarioRepositoryTest {

	private UsuarioRepository repositorioDeUsuarios;
	
	private Administrador administradorDePrueba;
	private Administrador administrador2DePrueba;

	@Before
	public void inicializar() {
		repositorioDeUsuarios = new UsuarioRepository();
		
		administradorDePrueba = new Administrador("administrador@poi.com.ar", "admin", "1234");
		administrador2DePrueba = new Administrador("otroadministrador@poi.com.ar", "otroAdmin", "asdf");
	}
	
	@Test
	public void buscarUsuarioPorNombreYPassTest() {
		Repositorio.obtenerRepositorioANivelThread().persistir(administradorDePrueba);
		
		Assert.assertEquals(repositorioDeUsuarios.obtenerUsuarioPor("admin", "1234"), administradorDePrueba);
	}
	
	@Test
	public void buscarPorOtroUsuarioConNombreYPassTest() {
		Repositorio.obtenerRepositorioANivelThread().persistir(administrador2DePrueba);
		
		Assert.assertEquals(repositorioDeUsuarios.obtenerUsuarioPor("otroAdmin", "asdf"), administrador2DePrueba);
	}
	
	@AfterClass
	public static void guardarYCerrarSesion() {
		Repositorio.obtenerRepositorioANivelThread().cerrarSesion();
	}
}