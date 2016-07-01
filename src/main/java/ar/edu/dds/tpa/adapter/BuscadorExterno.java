package ar.edu.dds.tpa.adapter;

import java.util.List;

import ar.edu.dds.tpa.model.PuntoDeInteres;

public interface BuscadorExterno {
	public List<PuntoDeInteres> buscarPuntosDeInteresDelServicio(List<String> palabrasDeBusqueda);
}