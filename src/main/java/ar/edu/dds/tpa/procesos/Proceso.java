package ar.edu.dds.tpa.procesos;

import ar.edu.dds.tpa.procesos.estado.EstadoDeProceso;
import ar.edu.dds.tpa.procesos.fallos.AccionEnCasoDeFalloDeProceso;

public abstract class Proceso implements Runnable {
	private EstadoDeProceso estado;
	private AccionEnCasoDeFalloDeProceso accionEnCasoDeFallo;
	
	public void run() {
		ejecutar();
	}
	
	public abstract void ejecutar();
	
	public void fallar() {
		accionEnCasoDeFallo.ejecutar();
	}
	
	public void enCasoDeFalloHacer(AccionEnCasoDeFalloDeProceso unaAccionEnCasoDeFallo) {
		accionEnCasoDeFallo = unaAccionEnCasoDeFallo;
	}
	
	public boolean estaEnEjecucion() {
		return estado.estaEnEjecucion();
	}
}