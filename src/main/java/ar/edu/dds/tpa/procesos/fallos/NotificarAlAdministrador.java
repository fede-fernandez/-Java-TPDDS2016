package ar.edu.dds.tpa.procesos.fallos;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.model.usuario.Administrador;
import ar.edu.dds.tpa.procesos.Proceso;

public class NotificarAlAdministrador implements AccionEnCasoDeFalloDeProceso {

	private Proceso proceso;
	private Administrador administradorANotificar;
	private EnviadorDeMail enviadorDeMail;

	public NotificarAlAdministrador(Proceso proceso, Administrador administradorANotificar,
			EnviadorDeMail enviadorDeMail) {
		this.proceso = proceso;
		this.administradorANotificar = administradorANotificar;
		this.enviadorDeMail = enviadorDeMail;
	}

	@Override
	public void ejecutar() {
		enviadorDeMail.enviarMail(administradorANotificar.getMail(), "Fallo un proceso",
				"El proceso: " + proceso.toString() + " fallo en el momento " + LocalDateTime.now());
	}
}