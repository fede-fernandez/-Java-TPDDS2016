package ar.edu.dds.tpa.observer;

import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Busqueda;
import ar.edu.dds.tpa.service.MailService;

public class NotificadorDeBusquedaLenta implements BusquedaObserver {
	private double tiempoMaximoDeDemoraEnSegundos;
	private Administrador administradorAContactar;
	private MailService servicioDeEnvioDeMail;

	public NotificadorDeBusquedaLenta(double tiempoMaximoDeDemora, MailService servicioDeEnvioDeMail, Administrador administradorAContactar) {
		this.tiempoMaximoDeDemoraEnSegundos = tiempoMaximoDeDemora;
		this.servicioDeEnvioDeMail = servicioDeEnvioDeMail;
		this.administradorAContactar = administradorAContactar;
	}

	@Override
	public void informar(Busqueda unaBusquedaRealizada) {
		if (unaBusquedaRealizada.getTiempoDeRespuesta() > tiempoMaximoDeDemoraEnSegundos) {
			String asuntoDelMensaje = "[ALERTA] Una busqueda demoro mucho tiempo.";
			String mensajeAEnviar = "La busqueda de la palabra: " + unaBusquedaRealizada.getTextoBuscado() + " demoro "
					+ unaBusquedaRealizada.getTiempoDeRespuesta() + " segundos.";
			
			servicioDeEnvioDeMail.enviarMailA(administradorAContactar.getMail(), asuntoDelMensaje, mensajeAEnviar);
		}
	}
}