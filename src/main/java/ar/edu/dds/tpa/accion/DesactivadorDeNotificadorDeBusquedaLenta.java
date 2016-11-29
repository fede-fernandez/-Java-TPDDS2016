package ar.edu.dds.tpa.accion;

import ar.edu.dds.tpa.model.usuario.Terminal;
import ar.edu.dds.tpa.observer.BusquedaObserver;

public class DesactivadorDeNotificadorDeBusquedaLenta implements AccionDeUsuario {

	private BusquedaObserver observadorDeBusqueda;

	public DesactivadorDeNotificadorDeBusquedaLenta(BusquedaObserver observadorDeBusqueda) {
		this.observadorDeBusqueda = observadorDeBusqueda;
	}

	public void realizarAccion(Terminal unaTerminal) {
		unaTerminal.quitarObservadorDeBusqueda(observadorDeBusqueda);
	}
}