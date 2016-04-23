package ar.edu.dds.tpa.model;

public class KioscoDeDiario extends Rubro {

	public KioscoDeDiario(String nombreRubro) {
		super(nombreRubro);
	}

	@Override
	public double radioDeCercania() {
		return 2.00;
	}
}
