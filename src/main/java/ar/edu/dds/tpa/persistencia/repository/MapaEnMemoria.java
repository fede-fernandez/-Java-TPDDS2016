package ar.edu.dds.tpa.persistencia.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.dds.tpa.model.PuntoDeInteres;

public class MapaEnMemoria implements Mapa {

	private List<PuntoDeInteres> puntosDeInteres;

	public MapaEnMemoria() {
		puntosDeInteres = new ArrayList<PuntoDeInteres>();
	}

	@Override
	public void agregar(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.add(unPuntoDeInteres);
	}

	@Override
	public void agregar(List<PuntoDeInteres> variosPuntosDeInteres) {
		puntosDeInteres.addAll(variosPuntosDeInteres);
	}

	@Override
	public void remover(PuntoDeInteres unPuntoDeInteres) {
		puntosDeInteres.remove(unPuntoDeInteres);
	}

	@Override
	public void removerPor(Integer id) {
		puntosDeInteres.remove(obtenerPor(id));
	}

	@Override
	public void modificar(PuntoDeInteres unPuntoDeInteres) {
		// El punto de interes ya se encuentra modificado en memoria por
		// identidad de objetos
	}

	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteres() {
		return puntosDeInteres;
	}

	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteresPorTipoYNombre(Class<? extends PuntoDeInteres> tipo,
			String nombre) {
		return puntosDeInteres.stream()
				.filter(poi -> tipo.isAssignableFrom(poi.getClass()) && poi.getNombre().contains(nombre))
				.collect(Collectors.toList());
	}

	@Override
	public PuntoDeInteres obtenerPor(Integer id) {
		return puntosDeInteres.stream().filter(poi -> poi.getId() == id).findFirst().get();
	}

	@Override
	public void darDeBaja(Integer idDelPuntoDeInteres, LocalDateTime fechaDeBaja) {
		PuntoDeInteres puntoDeInteresADarDeBaja = puntosDeInteres.stream()
				.filter(unPuntoDeInteres -> unPuntoDeInteres.getId().equals(idDelPuntoDeInteres)).findFirst().get();
		puntoDeInteresADarDeBaja.setActivo(false);
		puntoDeInteresADarDeBaja.setFechaDeBaja(fechaDeBaja);
	}
}