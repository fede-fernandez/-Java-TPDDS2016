package ar.edu.dds.tpa.service;

public interface MailService {
	public void enviarMailA(String direccionDeMail, String asunto, String mensaje);
}