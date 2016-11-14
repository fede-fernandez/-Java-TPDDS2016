package ar.edu.dds.tpa.procesos;

import java.util.List;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.accion.AccionDeUsuario;
import ar.edu.dds.tpa.criterio.Criterio;

public class GestorDeAccionesDeTerminales extends Proceso {

	private List<Terminal> terminales;
	private Criterio criterio;
	private AccionDeUsuario accion;

	public GestorDeAccionesDeTerminales(List<Terminal> terminales, Criterio criterio, AccionDeUsuario accion) {
		this.terminales = terminales;
		this.criterio = criterio;
		this.accion = accion;
	}

	public void ejecutar() {
		terminales.stream().filter(unaTerminal -> criterio.filtrarTerminales(unaTerminal))
				.forEach(unaTerminalFiltrada -> accion.realizarAccion(unaTerminalFiltrada));

		this.finalizar(this.cantidadDeUsuariosAfectados());
	}

	private int cantidadDeUsuariosAfectados() {
		return (int) terminales.stream().filter(unaTerminal -> criterio.filtrarTerminales(unaTerminal)).count();
	}

}