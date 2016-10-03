package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

	private List<PuntoDeInteres> puntosDeInteres;

	public Mapa() {
		puntosDeInteres = new ArrayList<PuntoDeInteres>();
	}

	public void agregar(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.add(unPuntoDeInteres);
	}
	
	public void agregarListaPuntoDeInteres(List<PuntoDeInteres> listaPuntoDeInteres) {
		puntosDeInteres = listaPuntoDeInteres;
	}

	public void sacar(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.remove(unPuntoDeInteres);
	}

	public void modificar(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado) {
		puntosDeInteres.remove(unPuntoDeInteres);
		puntosDeInteres.add(puntoDeInteresModificado);
	}

	public List<PuntoDeInteres> obtenerPuntosDeInteres() {
		return puntosDeInteres;
	}

	public void darDeBaja(BajaPuntoDeInteres puntoADarDeBaja) {
		puntosDeInteres.stream().filter(puntoADarDeBaja::equals).findFirst()
				.ifPresent(punto -> punto.setFechaBaja(puntoADarDeBaja.getFechaBaja()));
	}
}