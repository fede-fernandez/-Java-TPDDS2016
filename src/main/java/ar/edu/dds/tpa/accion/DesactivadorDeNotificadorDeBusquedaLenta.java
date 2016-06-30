package ar.edu.dds.tpa.accion;

import ar.edu.dds.tpa.model.Usuario;
import ar.edu.dds.tpa.observer.BusquedaObserver;

public class DesactivadorDeNotificadorDeBusquedaLenta {
	
	public void desactivarNotificadorDeBusquedaLenta(Usuario unUsuario, BusquedaObserver unObservador){
		unUsuario.quitarObservadorDeBusqueda(unObservador);
	}

}
