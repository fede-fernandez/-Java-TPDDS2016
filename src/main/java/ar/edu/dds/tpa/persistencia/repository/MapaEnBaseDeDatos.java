package ar.edu.dds.tpa.persistencia.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.Persistible;

public class MapaEnBaseDeDatos implements Mapa, Persistible {

	@Override
	public void agregar(PuntoDeInteres unPuntoDeInteres) {
		repositorio.persistir(unPuntoDeInteres);
	}

	@Override
	public void agregar(List<PuntoDeInteres> variosPuntosDeInteres) {
		variosPuntosDeInteres.forEach(unPuntoDeInteres -> repositorio.persistir(unPuntoDeInteres));
	}

	@Override
	public void remover(PuntoDeInteres unPuntoDeInteres) {
		repositorio.eliminar(unPuntoDeInteres);
	}

	@Override
	public void removerPor(Integer id) {
		repositorio.eliminar(repositorio.buscarPorID(PuntoDeInteres.class, id));
	}

	@Override
	public void modificar(PuntoDeInteres unPuntoDeInteres) {
		repositorio.persistir(unPuntoDeInteres);
	}

	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteres() {
		return repositorio.traerTodos(PuntoDeInteres.class);
	}

	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteresPorTipoYNombre(Class<? extends PuntoDeInteres> tipo,
			String nombre) {
		return this.obtenerPuntosDeInteres().stream()
				.filter(poi -> tipo.isAssignableFrom(poi.getClass())
						&& (nombre == null || StringUtils.containsIgnoreCase(poi.getNombre(), nombre)))
				.collect(Collectors.toList());
	}

	@Override
	public PuntoDeInteres obtenerPor(Integer id) {
		return repositorio.buscarPorID(PuntoDeInteres.class, id);
	}

	@Override
	public void darDeBaja(Integer idDelPuntoDeInteres, LocalDateTime fechaDeBaja) {
		PuntoDeInteres puntoDeInteresADarDeBaja = repositorio.buscarPorID(PuntoDeInteres.class, idDelPuntoDeInteres);
		puntoDeInteresADarDeBaja.setFechaDeBaja(fechaDeBaja);
		puntoDeInteresADarDeBaja.setActivo(false);
		repositorio.persistir(puntoDeInteresADarDeBaja);
	}
}