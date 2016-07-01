package ar.edu.dds.tpa.procesos.estado;

public class Finalizado implements EstadoDeProceso {
	@Override
	public boolean estaEnEjecucion() {
		return false;
	}

	@Override
	public boolean finalizo() {
		return true;
	}

	@Override
	public String nombreDelEstado() {
		return "Finalizado";
	}
}