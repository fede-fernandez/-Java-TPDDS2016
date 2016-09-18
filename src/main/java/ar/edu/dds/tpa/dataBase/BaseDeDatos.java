package ar.edu.dds.tpa.dataBase;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.PuntoDeInteresConServicios;


public class BaseDeDatos implements WithGlobalEntityManager {

	public void guardar(PuntoDeInteres unPunto){
		entityManager().persist(unPunto);
	}
	
	public PuntoDeInteres obtenerPuntoInteres(Long id_pi){
		return entityManager().find(PuntoDeInteres.class, id_pi);
	}
	
	
	public List<PuntoDeInteres> todosPOIs(){
		return (List<PuntoDeInteres>) entityManager().createQuery("from PuntoDeInteres").getResultList();
	}
	
	public PuntoDeInteresConServicios obtenerPuntoDeInteresConServicios(Long id_pi){
		return entityManager().find(PuntoDeInteresConServicios.class, id_pi);
	}
	
	
	
}
