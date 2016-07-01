package ar.edu.dds.tpa.procesos.estado;

public interface EstadoDeProceso {
	public boolean estaEnEjecucion();

	public boolean finalizo();

	public String nombreDelEstado();
}