package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.persistencia.Persistible;

public class GestorDeAdministradores implements Persistible {

		private List<Administrador> administradores;

		public GestorDeAdministradores() {
			administradores = new ArrayList<Administrador>();
		}

		public void registrar(Administrador unAdministrador) {
			administradores.add(unAdministrador);
			persistidor.persistir(unAdministrador);
		}

		public Administrador traerAdministradorPor(int id) {
			return persistidor.buscarPorID(Administrador.class, id);
		}

		public List<Administrador> traerAdministradores() {
			return persistidor.traerTodos(Administrador.class);
		}
}

