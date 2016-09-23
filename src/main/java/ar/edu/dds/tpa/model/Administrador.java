package ar.edu.dds.tpa.model;

import javax.persistence.*;

@Entity
public class Administrador {

	@Id
	@GeneratedValue
	private Integer id;

	private String mail;

	public Administrador() {

	}

	public Administrador(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public String getMail() {
		return mail;
	}

	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, Mapa enUnMapa) {
		enUnMapa.agregar(unPuntoDeInteres);
	}

	public void sacarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, Mapa enUnMapa) {
		enUnMapa.sacar(unPuntoDeInteres);
	}

	public void modificarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado,
			Mapa enUnMapa) {
		enUnMapa.modificar(unPuntoDeInteres, puntoDeInteresModificado);
	}
}