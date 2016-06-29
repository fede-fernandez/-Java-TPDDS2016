package ar.edu.dds.tpa.adapter;

import ar.edu.dds.tpa.service.MailService;

public class MailServiceAdapter {
	private MailService servicioDeMail;
	
	public void enviarMail(String direccionDeMail, String asunto, String mensaje) {
		servicioDeMail.enviarMailA(direccionDeMail, asunto, mensaje);
	}
}