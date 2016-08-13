package ar.edu.dds.tpa.model;

public class Administrador {
	private String mail;
	
	public Administrador(String mail) {
		this.mail = mail;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, Mapa enUnMapa) {
		enUnMapa.agregarPuntoDeInteres(unPuntoDeInteres);
	}
	
	public void sacarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, Mapa enUnMapa) {
		enUnMapa.sacarPuntoDeInteres(unPuntoDeInteres);
	}
	
	public void modificarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado, Mapa enUnMapa) {
		enUnMapa.modificarPuntoDeInteres(unPuntoDeInteres, puntoDeInteresModificado);
	}	
}