package ar.edu.dds.tpa.procesos;

public class ProcesoQueFallaImpostor extends Proceso {
	private int vecesQueSeEjecuto = 0;
	
	@Override
	public void ejecutar() {
		vecesQueSeEjecuto++;
		fallar();
	}
	
	public int vecesQueSeEjecuto() {
		return vecesQueSeEjecuto;
	}
}