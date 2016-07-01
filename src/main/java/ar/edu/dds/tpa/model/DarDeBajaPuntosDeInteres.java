package ar.edu.dds.tpa.model;

public class DarDeBajaPuntosDeInteres {

	ServicioPuntosDeInteresBajados servicioBajas;
	Mapa mapa;
	
	
	public ServicioPuntosDeInteresBajados getServicioBajas() {
		return servicioBajas;
	}

	public void setServicioBajas(ServicioPuntosDeInteresBajados servicioBajas) {
		this.servicioBajas = servicioBajas;
	}

	public void ejecutar(){
		servicioBajas.obtenerPuntosABajar().stream().forEach(mapa::darDeBaja);
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
}
