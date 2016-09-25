package ar.edu.dds.tpa.procesos;

import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ServicioPuntosDeInteresBajados;
import ar.edu.dds.tpa.procesos.Proceso;

public class DarDeBajaPuntosDeInteres extends Proceso {

	ServicioPuntosDeInteresBajados servicioBajas;
	Mapa mapa;

	public ServicioPuntosDeInteresBajados getServicioBajas() {
		return servicioBajas;
	}

	public void setServicioBajas(ServicioPuntosDeInteresBajados servicioBajas) {
		this.servicioBajas = servicioBajas;
	}

	public void ejecutar() {
		servicioBajas.obtenerPuntosADarDeBaja().stream().forEach(mapa::darDeBaja);
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
}
