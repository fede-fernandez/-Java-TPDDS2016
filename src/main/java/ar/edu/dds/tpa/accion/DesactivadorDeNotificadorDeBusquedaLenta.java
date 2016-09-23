package ar.edu.dds.tpa.accion;

import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.observer.BusquedaObserver;

public class DesactivadorDeNotificadorDeBusquedaLenta implements AccionDeUsuario {

	private BusquedaObserver observadorDeBusqueda;

	public DesactivadorDeNotificadorDeBusquedaLenta(BusquedaObserver observadorDeBusqueda) {
		this.observadorDeBusqueda = observadorDeBusqueda;
	}

	public void realizarAccion(Usuario unUsuario) {
		unUsuario.quitarObservadorDeBusqueda(observadorDeBusqueda);
	}
}