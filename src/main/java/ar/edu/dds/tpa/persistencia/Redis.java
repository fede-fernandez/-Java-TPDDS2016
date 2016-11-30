package ar.edu.dds.tpa.persistencia;

import ar.edu.dds.tpa.properties.Propiedades;
import redis.clients.jedis.Jedis;

public class Redis implements RepositorioCache {
	
	private static Redis instancia;
	private Jedis conexionRedis;
	private Propiedades propiedadesDeRedis;
	
	private Redis() {
		propiedadesDeRedis = new Propiedades("resources/Redis.properties");
		conexionRedis = new Jedis(propiedadesDeRedis.obtenerValorDe("URLBaseDeDatosRedis"));
	}
	
	public static Redis obtenerConexion() {
		if(instancia == null) {
			instancia = new Redis();
		}
		return instancia;
	}
	
	public void cerrarConexion() {
		conexionRedis.close();
	}

	public String consultarValorPor(String unaClave) {
		return conexionRedis.get(unaClave);
	}

	public void insertar(String unaClave, String unValor) {
		conexionRedis.set(unaClave, unValor);
	}
}