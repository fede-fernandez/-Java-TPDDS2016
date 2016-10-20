package ar.edu.dds.tpa.service;

import java.util.List;

import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.persistencia.RepositorioCache;

public abstract class BuscadorExterno {
	public boolean soportaCache;
	public RepositorioCache repositorioCache;
	
	public abstract List<PuntoDeInteres> buscarPor(List<String> palabrasDeBusqueda);
	
	public BuscadorExterno(boolean soportaCache) {
		this.soportaCache = soportaCache;
	}
	
	public boolean soportaCache() {
		return soportaCache;
	}
	
	public void setRepositorioCache(RepositorioCache repositorioCache) {
		this.repositorioCache = repositorioCache;
	}
	
	public void establecerCacheDe(String unServicio, String datosAGuardar) {
		repositorioCache.insertar(unServicio, datosAGuardar);
	}
	
	public String consultarCacheDe(String unServicio) {
		return repositorioCache.consultarValorPor(unServicio);
	}
}