package ar.edu.dds.tpa.procesos.estado;

public class EnEspera implements EstadoDeProceso {
	@Override
	public boolean estaEnEjecucion() {
		return false;
	}

	@Override
	public boolean finalizo() {
		return false;
	}

	@Override
	public String nombreDelEstado() {
		return "En espera";
	}
}