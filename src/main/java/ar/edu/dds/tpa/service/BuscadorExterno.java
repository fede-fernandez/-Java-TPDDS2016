package ar.edu.dds.tpa.service;

import java.util.List;

import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.RepositorioCache;

public abstract class BuscadorExterno {
	public boolean soportaCache = false;
	public RepositorioCache repositorioCache;
	
	public abstract List<PuntoDeInteres> buscarPor(List<String> palabrasDeBusqueda);
	
	@SuppressWarnings("unchecked")
	public <T> T conSoporteCache(RepositorioCache repositorioCache) {
		soportaCache = true;
		this.repositorioCache = repositorioCache;
		return (T) this;
	}
	
	public boolean soportaCache() {
		return soportaCache;
	}
	
	public void establecerCacheDe(String unServicio, String datosAGuardar) {
		repositorioCache.insertar(unServicio, datosAGuardar);
	}
	
	public String consultarCacheDe(String unServicio) {
		return repositorioCache.consultarValorPor(unServicio);
	}
}