package ar.edu.dds.tpa.model.usuario;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

	private String mail;

	public Administrador() {

	}

	public Administrador(String mail) {
		this.mail = mail;
	}

	public Administrador(String mail, String nombreDeUsuario, String password) {
		super(nombreDeUsuario, password);
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String getUrl() {
		return "/puntosDeInteres";
	}
}