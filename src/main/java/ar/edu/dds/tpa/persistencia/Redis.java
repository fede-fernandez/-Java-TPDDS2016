package ar.edu.dds.tpa.persistencia;

import ar.edu.dds.tpa.properties.Propiedades;
import redis.clients.jedis.Jedis;

public class Redis implements RepositorioCache {
	private static Jedis conexionRedis;
	private Propiedades propiedadesDeRedis;

	public Redis() {
		propiedadesDeRedis = new Propiedades("resources/Redis.properties");
	}

	public Jedis obtenerSesion() {
		if (conexionRedis == null) {
			conexionRedis = new Jedis(propiedadesDeRedis.obtenerValorDe("URLBaseDeDatosRedis"));
		}
		return conexionRedis;
	}

	public void cerrarSesion() {
		conexionRedis.close();
	}

	public String consultarValorPor(String unaClave) {
		return obtenerSesion().get(unaClave);
	}

	public void insertar(String unaClave, String unValor) {
		obtenerSesion().set(unaClave, unValor);
	}
}