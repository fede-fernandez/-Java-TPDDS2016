package ar.edu.dds.tpa.persistencia.repository;

import java.util.HashMap;
import java.util.Map;

import ar.edu.dds.tpa.model.usuario.Usuario;
import ar.edu.dds.tpa.persistencia.Persistible;

public class UsuarioRepository implements Persistible {

	public Usuario loginDeUsuario(String usuario, String contrasenia) {
		Map<String, String> parametros = new HashMap<>();
		parametros.put("usuario", usuario);
		parametros.put("password", contrasenia);
		return repositorio.ejecutarQueryGetPrimerResultado("from Usuario where usuario=:usuario and password=:password",
				parametros);
	}
}
