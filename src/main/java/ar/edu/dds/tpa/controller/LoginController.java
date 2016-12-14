package ar.edu.dds.tpa.controller;

import javax.persistence.NoResultException;

import ar.edu.dds.tpa.model.usuario.Usuario;
import ar.edu.dds.tpa.persistencia.Persistible;
import ar.edu.dds.tpa.persistencia.repository.UsuarioRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController implements Persistible {

	public ModelAndView mostrarLogin(Request request, Response response) {

		return new ModelAndView(null, "Login/login.hbs");
	}

	public ModelAndView login(Request request, Response response) {

		String nick = request.queryParams("usuario");
		String pass = request.queryParams("pass");

		try {
			Usuario usuario = new UsuarioRepository().loginDeUsuario(nick, pass);

			request.session().attribute("usuario", usuario);
			response.redirect(usuario.getUrl());
			return null;
		} catch (NoResultException e) {
			return new ModelAndView(null, "Login/loginFallido.hbs");
		}
	}
}