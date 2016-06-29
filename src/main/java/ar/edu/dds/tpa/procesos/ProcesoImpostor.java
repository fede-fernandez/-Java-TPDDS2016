package ar.edu.dds.tpa.procesos;

public class ProcesoImpostor extends Proceso {
	private boolean seLlamoAlProcesoImpostor = false;

	@Override
	public void ejecutar() {
		seLlamoAlProcesoImpostor = true;
	}
	
	public boolean seLlamoAlProcesoImpostor() {
		return seLlamoAlProcesoImpostor;
	}
}