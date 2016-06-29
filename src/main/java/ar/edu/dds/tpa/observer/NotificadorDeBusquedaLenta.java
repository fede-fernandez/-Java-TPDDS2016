package ar.edu.dds.tpa.observer;

import ar.edu.dds.tpa.adapter.EnvioDeMail;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Busqueda;

public class NotificadorDeBusquedaLenta implements BusquedaObserver {
	private double tiempoMaximoDeDemoraEnSegundos;
	private Administrador administradorAContactar;
	private EnvioDeMail enviadorDeMails;

	public NotificadorDeBusquedaLenta(double tiempoMaximoDeDemora, EnvioDeMail enviadorDeMails, Administrador administradorAContactar) {
		this.tiempoMaximoDeDemoraEnSegundos = tiempoMaximoDeDemora;
		this.administradorAContactar = administradorAContactar;
		this.enviadorDeMails = enviadorDeMails;
	}

	@Override
	public void informar(Busqueda unaBusquedaRealizada) {
		if (unaBusquedaRealizada.getTiempoDeRespuesta() > tiempoMaximoDeDemoraEnSegundos) {
			String asuntoDelMensaje = "[ALERTA] Una busqueda demoro mucho tiempo.";
			String mensajeAEnviar = "La busqueda de la palabra: " + unaBusquedaRealizada.getTextoBuscado() + " demoro "
					+ unaBusquedaRealizada.getTiempoDeRespuesta() + " segundos.";
			
			enviadorDeMails.enviarMail(administradorAContactar.getMail(), asuntoDelMensaje, mensajeAEnviar);
		}
	}
}