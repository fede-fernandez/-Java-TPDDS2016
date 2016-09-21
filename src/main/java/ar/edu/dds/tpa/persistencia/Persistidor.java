package ar.edu.dds.tpa.persistencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Persistidor {

	private SessionFactory sessionFactory;
	private Transaction transaccion;
	private Session sesion;

	public Persistidor() {
		sessionFactory = new Configuration().configure("Hibernate.xml").buildSessionFactory();
	}

	public void persistir(Object unObjeto) {
		abrirSesion();
		iniciarTransaccion();
		sesion.save(unObjeto);
		commitear();
		cerrarSesion();
	}

	public void eliminar(Object unObjeto) {
		abrirSesion();
		iniciarTransaccion();
		sesion.remove(unObjeto);
		commitear();
		cerrarSesion();
	}

	public <T> T buscarPorID(Class<T> clase, int id) {
		abrirSesion();
		iniciarTransaccion();
		T objetoEncontrado = sesion.find(clase, id);
		cerrarSesion();
		return objetoEncontrado;
	}

	public <T> List<T> traerTodos(Class<T> clase) {
		abrirSesion();
		iniciarTransaccion();
		@SuppressWarnings("unchecked")
		List<T> objetosEncontrados = sesion.createQuery("from " + clase.getName()).getResultList();
		cerrarSesion();
		return objetosEncontrados;
	}

	public Session abrirSesion() {
		sesion = sessionFactory.openSession();
		return sesion;
	}
	
	public void cerrarSesion() {
		sessionFactory.close();
	}

	public void iniciarTransaccion() {
		transaccion = sesion.beginTransaction();
	}

	public void commitear() {
		transaccion.commit();
	}

	public void rollbackear() {
		transaccion.rollback();
	}
}