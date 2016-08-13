package ar.edu.dds.tpa.procesos;

import java.time.LocalDateTime;

import ar.edu.dds.tpa.procesos.fallos.AccionEnCasoDeFalloDeProceso;

public abstract class Proceso implements Runnable {
	private AccionEnCasoDeFalloDeProceso accionEnCasoDeFallo;
	private ResultadoDeEjecucion resultadoDeEjecucion = new ResultadoDeEjecucion(LocalDateTime.now());
	
	public void run() {
		ejecutar();
	}
	
	public abstract void ejecutar();
	
	public void finalizar(int cantidadDeElementosAfectados) {
		resultadoDeEjecucion.establecerComoFinalizado(cantidadDeElementosAfectados);
	}
	
	public void fallar() {
		accionEnCasoDeFallo.ejecutar();
	}
	
	public ResultadoDeEjecucion obtenerResultadoDeEjecucion() {
		return resultadoDeEjecucion;
	}
	
	public void enCasoDeFalloHacer(AccionEnCasoDeFalloDeProceso unaAccionEnCasoDeFallo) {
		accionEnCasoDeFallo = unaAccionEnCasoDeFallo;
	}
}