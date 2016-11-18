package ar.edu.dds.tpa.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ar.edu.dds.tpa.historial.HistorialDeBusqueda;
import ar.edu.dds.tpa.historial.HistorialDeBusquedaEnMemoria;
import ar.edu.dds.tpa.model.*;

public class Repositorio {

	private static Repositorio instancia;
	private static SessionFactory sessionFactory;
	private static Session sesion;

	private Repositorio() {
		sessionFactory = new Configuration().configure("Hibernate.xml").buildSessionFactory();
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
		}
		return sesion;
	}

	public void cerrarSesion() {
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

	public <T> List<T> traerTodos(Class<T> clase) {
		@SuppressWarnings("unchecked")
		List<T> objetosEncontrados = obtenerSesion().createQuery("from " + clase.getName()).getResultList();
		return objetosEncontrados;
	}

	public List<PuntoDeInteres> buscarTextoLibre(String texto, Terminal unaTerminal) {
		Mapa unMapa = new MapaEnMemoria();
		HistorialDeBusqueda unHistorial = new HistorialDeBusquedaEnMemoria();
		//TODO esto es para que no rompa, no tiene sentido instanciar un buscador cada vez que se haga una busqueda
		unMapa.agregar((List<PuntoDeInteres>) this.traerTodos(PuntoDeInteres.class));
		Buscador buscador = new Buscador(unMapa, unHistorial);
		List<PuntoDeInteres> pois = buscador.buscar(texto, unaTerminal);
		return pois;
	}

	public Terminal buscarTerminal(String nombre, String comuna) {
		List<Terminal> terminales = this.traerTodos(Terminal.class);
		return terminales.stream().filter(unaTerminal -> unaTerminal.getNombre().equals(nombre)
				&& unaTerminal.getComuna().getNombre().equals(comuna)).collect(Collectors.toList()).get(0);
	}
}