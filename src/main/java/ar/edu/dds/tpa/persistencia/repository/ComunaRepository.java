package ar.edu.dds.tpa.persistencia.repository;

import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.persistencia.Persistible;

import java.util.List;

public class ComunaRepository implements Persistible {

	public Comuna obtenerComunaPor(Integer id) {
		return repositorio.buscarPorID(Comuna.class, id);
	}

	public List<Comuna> obtenerComunas() {
		return repositorio.traerTodos(Comuna.class);
	}
}
