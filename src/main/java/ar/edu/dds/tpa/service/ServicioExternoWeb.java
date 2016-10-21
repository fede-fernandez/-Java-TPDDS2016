package ar.edu.dds.tpa.service;

import ar.edu.dds.tpa.persistencia.RepositorioCache;
import ar.edu.dds.tpa.properties.Propiedades;

public abstract class ServicioExternoWeb {

	private Propiedades propiedades;
	private boolean soportaCache = false;
	private RepositorioCache repositorioCache;

	public ServicioExternoWeb() {
		propiedades = new Propiedades("resources/ServiciosExternos.properties");
	}

	public Propiedades getPropiedades() {
		return propiedades;
	}

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