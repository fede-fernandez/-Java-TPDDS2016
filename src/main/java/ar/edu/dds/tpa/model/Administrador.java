package ar.edu.dds.tpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.excepcion.FaltaDePermisosExcepcion;

public class Administrador {
	private Mapa mapa;
	private List<Terminal> terminales;
	private String mail;
	
	public Administrador(String mail) {
		this.mail = mail;
		terminales = new ArrayList<Terminal>();
	}
	
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void agregarTerminal(Terminal unaTerminal) {
		terminales.add(unaTerminal);
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
	
	
	
	public int obtenerReporteDeBusquedaPorFecha(Terminal unaTerminal, LocalDate unaFecha) throws FaltaDePermisosExcepcion{
		return unaTerminal.cantidadDeBusquedasPorFecha(unaFecha);	
	}
	
	public List<Integer> obtenerReporteDeResultadosParcialesDeUnaBusqueda(Terminal unaTerminal, String unTexto) throws FaltaDePermisosExcepcion{
		return unaTerminal.resultadosParcialesDeUnaBusqueda(unTexto);
	}
	
	public int obtenerReporteDeResultadosTotalesDeUnaBusqueda(Terminal unaTerminal, String unTexto) throws FaltaDePermisosExcepcion{
		return unaTerminal.cantidadTotalDeResultadosDeUnaBusqueda(unTexto);
	}
}