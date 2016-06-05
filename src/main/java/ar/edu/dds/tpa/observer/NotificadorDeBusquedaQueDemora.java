package ar.edu.dds.tpa.observer;

import ar.edu.dds.tpa.model.BusquedaRealizada;
import ar.edu.dds.tpa.service.EnvioDeMailService;

public class NotificadorDeBusquedaQueDemora implements BusquedaObserver {
	private double tiempoMaximoDeDemoraEnSegundos;
	private String mailDelAdministrador;
	private EnvioDeMailService servicioDeEnvioDeMail;

	public NotificadorDeBusquedaQueDemora(double tiempoMaximoDeDemora, String mailDelAdministrador) {
		this.tiempoMaximoDeDemoraEnSegundos = tiempoMaximoDeDemora;
		this.mailDelAdministrador = mailDelAdministrador;
	}

	@Override
	public void informar(BusquedaRealizada unaBusquedaRealizada) {
		if (unaBusquedaRealizada.getTiempoDeRespuestaEnSegundos() > tiempoMaximoDeDemoraEnSegundos) {
			String asuntoDelMensaje = "[ALERTA] Una busqueda demoro mucho tiempo.";
			String mensajeAEnviar = "La busqueda de la palabra: " + unaBusquedaRealizada.getTextoBuscado() + " demoro "
					+ unaBusquedaRealizada.getTiempoDeRespuestaEnSegundos() + " segundos.";
			
			servicioDeEnvioDeMail.enviarMailA(mailDelAdministrador, asuntoDelMensaje, mensajeAEnviar);
		}
	}
}