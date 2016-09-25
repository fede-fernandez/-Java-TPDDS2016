package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.persistencia.Persistible;

public class GestorDeUsuarios implements Persistible {

	private List<Usuario> usuarios;

	public GestorDeUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}

	public void registrar(Usuario unUsuario) {
		usuarios.add(unUsuario);
		persistidor.persistir(unUsuario);
	}

	public Usuario traerUsuarioPor(int id) {
		return persistidor.buscarPorID(Usuario.class, id);
	}

	public List<Usuario> traerUsuarios() {
		return persistidor.traerTodos(Usuario.class);
	}
}