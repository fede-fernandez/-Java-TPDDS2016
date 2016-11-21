package ar.edu.dds.tpa.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ar.edu.dds.tpa.model.PuntoDeInteres;

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
	public void sacar(PuntoDeInteres unPuntoDeInteres) {
		repositorio.eliminar(unPuntoDeInteres);
	}

	@Override
	public void modificar(PuntoDeInteres unPuntoDeInteres, PuntoDeInteres puntoDeInteresModificado) {
		repositorio.persistir(puntoDeInteresModificado);
	}

	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteres() {
		return repositorio.traerTodos(PuntoDeInteres.class);
	}
	
	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteresPorTipoYNombre(Class<? extends PuntoDeInteres> tipo, String nombre){
		return this.obtenerPuntosDeInteres()
				.stream()
				.filter(poi -> tipo.isAssignableFrom(poi.getClass()) && (nombre == null || StringUtils.containsIgnoreCase(poi.getNombre(), nombre)))
				.collect(Collectors.toList());
	}
	
	@Override
	public PuntoDeInteres obtenerPorID(int id){
		return repositorio.buscarPorID(PuntoDeInteres.class, id);
		//return this.obtenerPuntosDeInteres().stream().filter(poi -> poi.getId() == id).findFirst().get();
	}
}