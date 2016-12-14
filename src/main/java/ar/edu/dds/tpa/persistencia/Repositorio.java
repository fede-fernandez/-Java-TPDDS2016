package ar.edu.dds.tpa.persistencia;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Repositorio {

	private static Repositorio instancia;
	private static SessionFactory sessionFactory;
	private static Session sesion;

	private Repositorio() {
		try {
			sessionFactory = new Configuration().configure("Hibernate.xml").buildSessionFactory();
		}
		catch (Exception excepcion){
			sessionFactory = new Configuration().configure("HibernateConDBEnMemoria.xml").buildSessionFactory();
		}
	}

	public static Repositorio obtenerRepositorio() {
		if (instancia == null) {
			instancia = new Repositorio();
		}
		return instancia;
	}

	public static Session obtenerSesion() {
		if (sesion == null || !sesion.isOpen()) {
			sesion = sessionFactory.openSession();
			sesion.getTransaction().begin();
		}
		return sesion;
	}

	public void cerrarSesion() {
		Transaction transaccion = obtenerSesion().getTransaction();
		if (transaccion.isActive()) {
			transaccion.commit();
		}
		sesion.close();
	}

	public void persistir(Object unObjeto) {
		obtenerSesion().saveOrUpdate(unObjeto);
	}

	public void eliminar(Object unObjeto) {
		obtenerSesion().delete(unObjeto);
	}

	public <T> T buscarPorID(Class<T> clase, int id) {
		return (T) obtenerSesion().find(clase, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> traerTodos(Class<T> clase) {
		List<T> objetosEncontrados = obtenerSesion().createQuery("from " + clase.getName()).getResultList();
		return objetosEncontrados;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> ejecutarQuery(String query) {
		Query queryAEjecutar = obtenerSesion().createQuery(query);
		return (List<T>) queryAEjecutar.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> ejecutarQuery(String query, Map<String, String> parametros) {
		Query queryAEjecutar = obtenerSesion().createQuery(query);
		parametros.forEach((parametro, valorDeParametro) -> queryAEjecutar.setParameter(parametro, valorDeParametro));
		return (List<T>) queryAEjecutar.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T ejecutarQueryGetPrimerResultado(String query) {
		Query queryAEjecutar = obtenerSesion().createQuery(query);
		return (T) queryAEjecutar.getSingleResult();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T ejecutarQueryGetPrimerResultado(String query, Map<String, String> parametros) {
		Query queryAEjecutar = obtenerSesion().createQuery(query);
		parametros.forEach((parametro, valorDeParametro) -> queryAEjecutar.setParameter(parametro, valorDeParametro));
		return (T) queryAEjecutar.getSingleResult();
	}
}