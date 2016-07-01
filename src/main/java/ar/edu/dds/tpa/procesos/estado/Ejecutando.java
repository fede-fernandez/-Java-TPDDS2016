package ar.edu.dds.tpa.procesos.estado;

public class Ejecutando implements EstadoDeProceso {
	@Override
	public boolean estaEnEjecucion() {
		return true;
	}

	@Override
	public boolean finalizo() {
		return false;
	}

	@Override
	public String nombreDelEstado() {
		return "Ejecucion";
	}
}