package ar.edu.dds.tpa.dataBase;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;


import ar.edu.dds.tpa.model.PuntoDeInteres;


public class BaseDeDatos implements WithGlobalEntityManager {

	public void guardar(PuntoDeInteres unPunto){
		entityManager().persist(unPunto);
	}
	
	public PuntoDeInteres obtenerPuntoInteres(Long id_pi){
		return entityManager().find(PuntoDeInteres.class, id_pi);
	}
	
	
}
