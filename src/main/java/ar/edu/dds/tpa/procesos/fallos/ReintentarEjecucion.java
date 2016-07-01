package ar.edu.dds.tpa.procesos.fallos;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.procesos.Proceso;

public class ReintentarEjecucion implements AccionEnCasoDeFalloDeProceso {
	private Proceso proceso;
	private int cantidadMaximaDeIntentos;
	private int intentosRealizados;
	boolean notificarPorMailSiFalla;
	private Administrador administradorANotificar;
	private EnviadorDeMail enviadorDeMail;

	public ReintentarEjecucion(Proceso proceso, int cantidadMaximaDeIntentos, boolean notificarPorMailSiFalla) {
		this.proceso = proceso;
		this.cantidadMaximaDeIntentos = cantidadMaximaDeIntentos;
		intentosRealizados = 0;
		this.notificarPorMailSiFalla = notificarPorMailSiFalla;
	}

	public void administradorANotificar(Administrador unAdministrador, EnviadorDeMail enviadorDeMail) {
		administradorANotificar = unAdministrador;
		this.enviadorDeMail = enviadorDeMail;
	}

	@Override
	public void ejecutar() {
		if (superoLaCantidadDeIntentos()) {
			if (notificarPorMailSiFalla) {
				NotificarAlAdministrador notificarAlAdministrador = new NotificarAlAdministrador(proceso,
						administradorANotificar, enviadorDeMail);
				notificarAlAdministrador.ejecutar();
			}
		} else {
			intentosRealizados = intentosRealizados + 1;
			proceso.ejecutar();	
		}

	}

	public boolean superoLaCantidadDeIntentos() {
		return intentosRealizados >= cantidadMaximaDeIntentos;
	}
}
