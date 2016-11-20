package ar.edu.dds.tpa.model;

import javax.persistence.*;

import ar.edu.dds.tpa.persistencia.Mapa;

@Entity
public class Administrador extends Usuario{

	private String mail;

	public Administrador() {

	}

	public Administrador(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail){
		this.mail = mail;
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
	@Override
	public String getUrl(){
		return "historial/consultas.hbs";
	}
}