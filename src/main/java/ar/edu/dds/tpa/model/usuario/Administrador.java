package ar.edu.dds.tpa.model.usuario;

import javax.persistence.*;

import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.repository.Mapa;

@Entity
public class Administrador extends Usuario {

	private String mail;

	public Administrador() {

	}

	public Administrador(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, Mapa enUnMapa) {
		enUnMapa.agregar(unPuntoDeInteres);
	}

	public void sacarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, Mapa enUnMapa) {
		enUnMapa.remover(unPuntoDeInteres);
	}

	public void modificarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado,
			Mapa enUnMapa) {
		enUnMapa.modificar(unPuntoDeInteres, puntoDeInteresModificado);
	}

	@Override
	public String getUrl() {
		return "/administracion/consultar";
	}
}