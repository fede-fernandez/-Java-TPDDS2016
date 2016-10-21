package ar.edu.dds.tpa.service;

import java.util.List;

import ar.edu.dds.tpa.model.PuntoDeInteres;

public interface BuscadorExterno {

	public List<PuntoDeInteres> buscarPor(List<String> palabrasDeBusqueda);

}