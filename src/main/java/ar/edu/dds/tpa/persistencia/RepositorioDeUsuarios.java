package ar.edu.dds.tpa.persistencia;

import org.hibernate.query.Query;

import ar.edu.dds.tpa.model.Usuario;

public class RepositorioDeUsuarios implements Persistible{
	
	public Usuario loginDeUsuario(String usuario, String contrasenia){
		@SuppressWarnings("rawtypes")
		Query query = Repositorio.obtenerSesion().createQuery("from " + Usuario.class.getName() + " where usuario=:usuario and password=:password");
		
		query.setParameter("usuario", usuario);
		query.setParameter("password", contrasenia);
		
		return (Usuario)query.getSingleResult();
		
	}
	
}
