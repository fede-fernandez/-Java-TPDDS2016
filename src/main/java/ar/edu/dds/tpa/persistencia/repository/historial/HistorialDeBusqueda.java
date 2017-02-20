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

//	public List<Busqueda> encontrarTodasLasBusquedasPorTextoBuscado(String unTexto);
//
//	public List<Busqueda> encontrarLasBusquedasEntreDosFechas(LocalDate fechaDeInicio, LocalDate fechaDeFin);
//
//	public List<Busqueda> encontrarLasBusquedasEntreDosFechasYPorTextoBuscado(String textoBuscado, LocalDate fechaDeInicio,
//			LocalDate fechaDeFin);
//
//	public List<Busqueda> encontrarLasBusquedasQueSeRealizaronEnUnaFecha(LocalDate unaFecha);
//
//	
//	
//	public List<Busqueda> encontrarLasBusquedasDeUnaTerminal(String nombreDeTerminal);
//	
//	public List<Busqueda> encontrarLasBusquedasPorCantidadDeResultados(int cantidadDeResultados);
	

}