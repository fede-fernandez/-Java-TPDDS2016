package ar.edu.dds.tpa.procesos;

import java.util.List;
import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.accion.AccionDeUsuario;
import ar.edu.dds.tpa.criterio.Criterio;

public class GestorDeAccionesDeUsuarios extends Proceso {

	private List<Usuario> usuarios;
	private Criterio criterio;
	private AccionDeUsuario accion;

	public GestorDeAccionesDeUsuarios(List<Usuario> usuarios, Criterio criterio, AccionDeUsuario accion) {
		this.usuarios = usuarios;
		this.criterio = criterio;
		this.accion = accion;
	}

	public void ejecutar() {
		usuarios.stream().filter(unUsuario -> criterio.filtrarUsuarios(unUsuario))
				.forEach(unUsuarioFiltrado -> accion.realizarAccion(unUsuarioFiltrado));

		this.finalizar(this.cantidadDeUsuariosAfectados());
	}

	private int cantidadDeUsuariosAfectados() {
		return (int) usuarios.stream().filter(unUsuario -> criterio.filtrarUsuarios(unUsuario)).count();
	}

}