package ar.edu.dds.tpa.model;

public class KioscoDeDiario implements Rubro {
	
	@Override
	public double radioDeCercania() {
		return 2.00;
	}

	@Override
	public String nombre() {
		return "Kiosco de Diario";
	}
}