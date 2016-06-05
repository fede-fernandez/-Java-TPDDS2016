package ar.edu.dds.tpa.excepcion;

public class FaltaDePermisosExcepcion extends Exception{

	
	private static final long serialVersionUID = 1L;

	public FaltaDePermisosExcepcion(String mensaje) {
		super(mensaje);
	}	

}
