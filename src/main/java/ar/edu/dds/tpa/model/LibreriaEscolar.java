package ar.edu.dds.tpa.model;

public class LibreriaEscolar extends Rubro {

	public LibreriaEscolar(String nombreRubro) {
		super(nombreRubro);
	}

	@Override
	public double radioDeCercania() {
		return 5.00;
	}
}
