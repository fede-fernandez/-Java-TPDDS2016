package ar.edu.dds.tpa.model;

import java.util.List;

public class Administrador {
	private Mapa mapa;
	private List<Terminal> terminales;
	
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public void agregarMapaATerminales() {
		terminales.forEach(unaTerminal -> unaTerminal.setMapa(mapa));
	}
	
	public void agregarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		mapa.agregarPuntoDeInteres(unPuntoDeInteres);
	}
	
	public void sacarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres) {
		mapa.sacarPuntoDeInteres(unPuntoDeInteres);
	}
	
	public void modificarPuntoDeInteres(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado) {
		mapa.modificarPuntoDeInteres(unPuntoDeInteres, puntoDeInteresModificado);
	}
}