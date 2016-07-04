package ar.edu.dds.tpa.procesos;

import java.time.LocalDateTime;

public class ResultadoDeEjecucion {
	private LocalDateTime horarioDeInicio;
	private int cantidadDeElementosAfectados;
	private boolean finalizoElProceso;

	public ResultadoDeEjecucion(LocalDateTime horarioDeInicio) {
		this.horarioDeInicio = horarioDeInicio;
		this.cantidadDeElementosAfectados = 0;
		finalizoElProceso = false;
	}
	
	public void establecerComoFinalizado(int cantidadDeElementosAfectados) {
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
		finalizoElProceso = true;
	}

	public LocalDateTime getHorarioDeInicio() {
		return horarioDeInicio;
	}

	public int getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}
	
	public boolean seFinalizoElProceso() {
		return finalizoElProceso;
	}
}