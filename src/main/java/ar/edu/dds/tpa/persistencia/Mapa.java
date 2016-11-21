package ar.edu.dds.tpa.persistencia;

import java.util.List;

import ar.edu.dds.tpa.model.PuntoDeInteres;

public interface Mapa {
	public void agregar(PuntoDeInteres unPuntoDeInteres);
	
	public void agregar(List<PuntoDeInteres> variosPuntosDeInteres);
	
	public void sacar(PuntoDeInteres unPuntoDeInteres);
	
	public void modificar(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado);
	
	public List<PuntoDeInteres> obtenerPuntosDeInteres();
	
	public List<PuntoDeInteres> obtenerPuntosDeInteresPorTipoYNombre(Class<? extends PuntoDeInteres> tipo, String nombre);

	public PuntoDeInteres obtenerPorID(int id);
}