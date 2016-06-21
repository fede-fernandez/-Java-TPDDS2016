package ar.edu.dds.tpa.service;

public class EnvioDeMailServiceImpostor implements EnvioDeMailService {
	private boolean seLlamoAlServicioDeEnvioDeMail = false;

	@Override
	public void enviarMailA(String direccionDeMail, String asunto, String mensaje) {
		seLlamoAlServicioDeEnvioDeMail = true;
	}
	
	public boolean seLlamoAlServicioDeEnvioDeMail() {
		return seLlamoAlServicioDeEnvioDeMail;
	}
}