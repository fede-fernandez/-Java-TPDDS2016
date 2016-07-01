package ar.edu.dds.tpa.procesos;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.procesos.estado.EstadoDeProceso;

public class ResultadoDeEjecucion {
	private LocalDateTime horarioDeInicio;
	private EstadoDeProceso estadoDeProceso;
	private int cantidadDeElementosAfectados;

	public ResultadoDeEjecucion(LocalDateTime horarioDeInicio, EstadoDeProceso estadoDeProceso) {
		this.horarioDeInicio = horarioDeInicio;
		this.estadoDeProceso = estadoDeProceso;
		this.cantidadDeElementosAfectados = 0;
	}

	public LocalDateTime getHorarioDeInicio() {
		return horarioDeInicio;
	}

	public EstadoDeProceso getEstadoDeProceso() {
		return estadoDeProceso;
	}

	public void setEstadoDeProceso(EstadoDeProceso estadoDeProceso) {
		this.estadoDeProceso = estadoDeProceso;
	}

	public int getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}

	public void setCantidadDeElementosAfectados(int cantidadDeElementosAfectados) {
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
	}
}