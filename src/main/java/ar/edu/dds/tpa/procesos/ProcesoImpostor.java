package ar.edu.dds.tpa.procesos;

public class ProcesoImpostor implements Proceso {
	private boolean seLlamoAlProcesoImpostor = false;

	@Override
	public void ejecutar() {
		seLlamoAlProcesoImpostor = true;
	}
	
	public boolean seLlamoAlProcesoImpostor() {
		return seLlamoAlProcesoImpostor;
	}

	@Override
	public void run() {
		ejecutar();
		
	}
}