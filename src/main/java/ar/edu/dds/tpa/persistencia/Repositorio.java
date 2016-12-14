package ar.edu.dds.tpa.persistencia;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Repositorio {

	private static ThreadLocal<Repositorio> instaciaThread = new ThreadLocal<Repositorio>();
	private static SessionFactory sessionFactory;
	private static Session sesion;

	private Repositorio() {
		try {
			sessionFactory = new Configuration().configure("Hibernate.xml").buildSessionFactory();
		} catch (Exception excepcion) {
			sessionFactory = new Configuration().configure("HibernateConDBEnMemoria.xml").buildSessionFactory();
		}
	}

	public static Repositorio obtenerRepositorioANivelThread() {
		Repositorio repositorio = instaciaThread.get();
		if (repositorio == null) {
			repositorio = new Repositorio();
			instaciaThread.set(repositorio);
		}
		return repositorio;
	}

	public static Session obtenerSesion() {
		if (sesion == null || !sesion.isOpen()) {
			sesion = sessionFactory.openSession();
		}
		return sesion;
	}

	public void cerrarSesion() {
		instaciaThread.set(null);
		sesion.close();
	}

	public void comenzarTransaccion() {
		obtenerSesion().getTransaction().begin();
	}

	public void realizarTransaccion() {
		Transaction transaccion = obtenerSesion().getTransaction();
		try {
			if (transaccion.isActive()) {
				transaccion.commit();
			}
		} catch (Exception excepcion) {
			rollbackearTransaccion();
		}
	}

	public void rollbackearTransaccion() {
		Transaction transaccion = obtenerSesion().getTransaction();
		if (transaccion.isActive()) {
			transaccion.rollback();
		}
	}

	public void persistir(Object unObjeto) {
		comenzarTransaccion();
		obtenerSesion().saveOrUpdate(unObjeto);
		realizarTransaccion();
	}

	public void eliminar(Object unObjeto) {
		comenzarTransaccion();
		obtenerSesion().delete(unObjeto);
		realizarTransaccion();
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
		;
		Query queryAEjecutar = obtenerSesion().createQuery(query);
		return (List<T>) queryAEjecutar.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> ejecutarQuery(String query, Map<String, String> parametros) {
		Query queryAEjecutar = obtenerSesion().createQuery(query);
		parametros.forEach((parametro, valorDeParametro) -> queryAEjecutar.setParameter(parametro, valorDeParametro));
		return (List<T>) queryAEjecutar.getResultList();
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T ejecutarQueryGetPrimerResultado(String query) {
		return (T) ejecutarQuery(query).get(0);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T ejecutarQueryGetPrimerResultado(String query, Map<String, String> parametros) {
		return (T) ejecutarQuery(query, parametros).get(0);
	}
}