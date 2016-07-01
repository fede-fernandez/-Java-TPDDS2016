package ar.edu.dds.tpa.observer;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Busqueda;

public class NotificadorDeBusquedaLenta implements BusquedaObserver {
	private double tiempoMaximoDeDemoraEnSegundos;
	private Administrador administradorAContactar;
	private EnviadorDeMail enviadorDeMail;

	public NotificadorDeBusquedaLenta(double tiempoMaximoDeDemora, EnviadorDeMail enviadorDeMail, Administrador administradorAContactar) {
		this.tiempoMaximoDeDemoraEnSegundos = tiempoMaximoDeDemora;
		this.administradorAContactar = administradorAContactar;
		this.enviadorDeMail = enviadorDeMail;
	}

	@Override
	public void informar(Busqueda unaBusquedaRealizada) {
		if (unaBusquedaRealizada.getTiempoDeRespuesta() > tiempoMaximoDeDemoraEnSegundos) {
			String asuntoDelMensaje = "[ALERTA] Una busqueda demoro mucho tiempo.";
			String mensajeAEnviar = "La busqueda de la palabra: " + unaBusquedaRealizada.getTextoBuscado() + " demoro "
					+ unaBusquedaRealizada.getTiempoDeRespuesta() + " segundos.";
			
			enviadorDeMail.enviarMail(administradorAContactar.getMail(), asuntoDelMensaje, mensajeAEnviar);
		}
	}
}