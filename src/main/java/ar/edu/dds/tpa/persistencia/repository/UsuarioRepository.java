package ar.edu.dds.tpa.persistencia.repository;

import org.hibernate.query.Query;

import ar.edu.dds.tpa.model.usuario.Usuario;
import ar.edu.dds.tpa.persistencia.Persistible;

public class UsuarioRepository implements Persistible{
	
	public Usuario loginDeUsuario(String usuario, String contrasenia){
		@SuppressWarnings("rawtypes")
		Query query = Repositorio.obtenerSesion().createQuery("from " + Usuario.class.getName() + " where usuario=:usuario and password=:password");
		
		query.setParameter("usuario", usuario);
		query.setParameter("password", contrasenia);
		
		return (Usuario)query.getSingleResult();
		
	}
	
}
