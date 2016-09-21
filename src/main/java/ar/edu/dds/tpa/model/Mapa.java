package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.persistencia.Persistible;

public class Mapa implements Persistible {
	private List<PuntoDeInteres> puntosDeInteres;

	public Mapa() {
		puntosDeInteres = new ArrayList<PuntoDeInteres>();
	}

	public void agregar(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.add(unPuntoDeInteres);
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
	
	public void almacenar(PuntoDeInteres unPuntoDeInteres) {
		agregar(unPuntoDeInteres);
		persistidor.persistir(unPuntoDeInteres);
	}
	
	public PuntoDeInteres traerPuntoDeInteresPor(int id) {
		return persistidor.buscarPorID(PuntoDeInteres.class, id);
	}
	
	public List<PuntoDeInteres> traerPuntosDeInteres() {
		return persistidor.traerTodos(PuntoDeInteres.class);
	}

	public void darDeBaja(BajaPuntoDeInteres puntoADarDeBaja) {
		puntosDeInteres.stream().filter(puntoADarDeBaja::equals).findFirst()
				.ifPresent(punto -> punto.setFechaBaja(puntoADarDeBaja.getFechaBaja()));
	}
}