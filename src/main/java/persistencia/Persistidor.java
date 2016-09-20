package persistencia;

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
	}

	public void eliminar(Object unObjeto) {
		abrirSesion();
		iniciarTransaccion();
		sesion.remove(unObjeto);
		commitear();
	}

	public <T> T buscarPorID(Class<T> clase, int id) {
		abrirSesion();
		iniciarTransaccion();
		return sesion.find(clase, id);
	}

	public List<?> traerTodos(Class<?> clase) {
		return sesion.createQuery("from " + clase.getName()).getResultList();
	}

	public Session abrirSesion() {
		sesion = sessionFactory.openSession();
		return sesion;
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