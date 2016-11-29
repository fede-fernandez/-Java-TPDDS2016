package ar.edu.dds.tpa.persistencia.repository;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.dds.tpa.model.PuntoDeInteres;

public interface Mapa {

	public void agregar(PuntoDeInteres unPuntoDeInteres);

	public void agregar(List<PuntoDeInteres> variosPuntosDeInteres);

	public void remover(PuntoDeInteres unPuntoDeInteres);

	public void removerPor(Integer id);

	public void darDeBaja(Integer idDelPuntoDeInteres, LocalDateTime fechaDeBaja);

	public void modificar(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado);

	public List<PuntoDeInteres> obtenerPuntosDeInteres();

	public List<PuntoDeInteres> obtenerPuntosDeInteresPorTipoYNombre(Class<? extends PuntoDeInteres> tipo,
			String nombre);

	public PuntoDeInteres obtenerPor(Integer id);
}