package ar.edu.dds.tpa.model;

public class LibreriaEscolar implements Rubro {
	
	@Override
	public double radioDeCercania() {
		return 5.00;
	}

	@Override
	public String nombre() {
		return "Libreria escolar";
	}
}