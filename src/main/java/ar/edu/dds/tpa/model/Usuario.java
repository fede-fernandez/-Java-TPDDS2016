package ar.edu.dds.tpa.model;

import javax.persistence.*;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String login;

	private String password;

	public Usuario() {

	}

	public Usuario(String login, String password) {
		this.login = login;
		this.password = password;
	}
}