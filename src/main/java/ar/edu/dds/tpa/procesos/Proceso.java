package ar.edu.dds.tpa.procesos;

public abstract class Proceso implements Runnable {
	public void run() {
		ejecutar();
	}
	public abstract void ejecutar();
}