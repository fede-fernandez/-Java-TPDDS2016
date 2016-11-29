package ar.edu.dds.tpa.persistencia;

import ar.edu.dds.tpa.persistencia.repository.Repositorio;

public interface Persistible {
	Repositorio repositorio = Repositorio.obtenerRepositorio();
}