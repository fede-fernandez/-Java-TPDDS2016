package ar.edu.dds.tpa.controller;

import javax.persistence.NoResultException;

import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController implements Persistible{
	
	public ModelAndView mostrarLogin(Request request, Response response){
		
		return new ModelAndView(null, "login/login.hbs");
	}

	public ModelAndView login(Request request, Response response){

		String nick = request.queryParams("usuario");
		String pass = request.queryParams("pass");
		
		try {
			Usuario usuario = new RepositorioDeUsuarios().loginDeUsuario(nick, pass);
			
			request.session().attribute("usuario",usuario);
			
			return new ModelAndView(null, usuario.getUrl());
		} catch (NoResultException e) {
			return new ModelAndView(null, "login/loginFallido.hbs");
		}				
	}
}
