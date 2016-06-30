package ar.edu.dds.tpa.procesos;

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
}
//Aplicar state
//estaEjecutando en estadoejecutado devuelve true, proceso tiene boolean y le manda ese mensaje al estado