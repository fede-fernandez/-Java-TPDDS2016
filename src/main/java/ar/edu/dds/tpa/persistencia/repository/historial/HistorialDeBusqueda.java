package ar.edu.dds.tpa.persistencia.repository.historial;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import ar.edu.dds.tpa.model.Busqueda;

public interface HistorialDeBusqueda {

	public void registrarBusqueda(Busqueda busqueda);
	
	public void eliminarBusqueda(Busqueda unaBusqueda);

	public List<Busqueda> traerTodasLasBusquedas();
	
	public Busqueda encontrarBusquedaPorId(String id);
	
	public Busqueda encontrarBusquedaPorId(ObjectId id);

	public List<Busqueda> ejecutarConsulta(Query<Busqueda> consulta);

}