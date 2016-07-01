package ar.edu.dds.tpa.procesos;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.procesos.estado.Ejecutando;
import ar.edu.dds.tpa.procesos.estado.EnEspera;
import ar.edu.dds.tpa.procesos.estado.EstadoDeProceso;
import ar.edu.dds.tpa.procesos.estado.Fallido;
import ar.edu.dds.tpa.procesos.estado.Finalizado;
import ar.edu.dds.tpa.procesos.fallos.AccionEnCasoDeFalloDeProceso;

public abstract class Proceso implements Runnable {
	private EstadoDeProceso estado = new EnEspera();
	private AccionEnCasoDeFalloDeProceso accionEnCasoDeFallo;
	private ResultadoDeEjecucion resultadoDeEjecucion = new ResultadoDeEjecucion(LocalDateTime.now(), estado);
	
	public void run() {
		cambiarEstado(new Ejecutando());
		ejecutar();
	}
	
	public abstract void ejecutar();
	
	public void finalizar(int cantidadDeElementosAfectados) {
		cambiarEstado(new Finalizado());
		resultadoDeEjecucion.setCantidadDeElementosAfectados(cantidadDeElementosAfectados);
	}
	
	public void cambiarEstado(EstadoDeProceso estado) {
		this.estado = estado;
	}
	
	public EstadoDeProceso obtenerEstado() {
		return estado;
	}
	
	public void fallar() {
		cambiarEstado(new Fallido());
		accionEnCasoDeFallo.ejecutar();
	}
	
	public void enCasoDeFalloHacer(AccionEnCasoDeFalloDeProceso unaAccionEnCasoDeFallo) {
		accionEnCasoDeFallo = unaAccionEnCasoDeFallo;
	}
	
	public ResultadoDeEjecucion obtenerResultadoDeEjecucion() {
		resultadoDeEjecucion.setEstadoDeProceso(estado);
		return resultadoDeEjecucion;
	}
	
	public boolean estaEnEjecucion() {
		return estado.estaEnEjecucion();
	}
}