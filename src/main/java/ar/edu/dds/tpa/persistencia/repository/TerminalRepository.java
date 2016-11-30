package ar.edu.dds.tpa.persistencia.repository;

import java.util.List;

import ar.edu.dds.tpa.model.Comuna;
import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.persistencia.Persistible;

public class TerminalRepository implements Persistible {

	public Terminal obtenerTerminalPorID(Integer id) {
		return repositorio.buscarPorID(Terminal.class, id);
	}

	public void agregarTerminal(Terminal unaTerminal) {
		repositorio.persistir(unaTerminal);
	}

	public void modificarTerminal(Integer idDeTerminal, Terminal terminalModificada) {
		Terminal terminalAModificar = obtenerTerminalPorID(idDeTerminal);
		terminalAModificar.setNombre(terminalModificada.getNombre());
		terminalAModificar.setCoordenadas(terminalModificada.getCoordenadas());
		terminalAModificar.setComuna(terminalModificada.getComuna());

		repositorio.persistir(terminalAModificar);
	}

	public void eliminarTerminal(Integer idDeTerminal) {
		repositorio.eliminar(obtenerTerminalPorID(idDeTerminal));
	}

	public List<Terminal> obtenerTerminales() {
		return repositorio.traerTodos(Terminal.class);
	}

	public List<Terminal> obtenerTerminalesPor(Integer numeroDeComuna) {
		List<Terminal> terminalesEncontradas = repositorio
				.ejecutarQuery("from Terminal as terminal terminal.comuna.numero = " + numeroDeComuna);
		return terminalesEncontradas;
	}

	public List<Comuna> obtenerComunas() {
		return repositorio.traerTodos(Comuna.class);
	}
}