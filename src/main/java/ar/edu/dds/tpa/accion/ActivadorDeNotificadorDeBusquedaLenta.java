package ar.edu.dds.tpa.accion;

import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.observer.BusquedaObserver;

public class ActivadorDeNotificadorDeBusquedaLenta implements AccionDeUsuario{
	
	private BusquedaObserver observadorDeBusqueda;
	
	public ActivadorDeNotificadorDeBusquedaLenta(BusquedaObserver observadorDeBusqueda) {
		this.observadorDeBusqueda = observadorDeBusqueda;
	}

	public void realizarAccion(Usuario unUsuario){
		unUsuario.agregarObservadorDeBusqueda(observadorDeBusqueda);
	}

}
