package ar.edu.dds.tpa.historial;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import ar.edu.dds.tpa.model.Busqueda;

public interface HistorialDeBusqueda {

	public void registrarBusqueda(Busqueda busqueda);

	public List<Busqueda> traerTodasLasBusquedas();

	public List<Busqueda> encontrarTodasLasBusquedasPorTextoBuscado(String unTexto);

	public List<Busqueda> encontrarLasBusquedasEntreDosFechas(LocalDate fechaDeInicio, LocalDate fechaDeFin);

	public List<Busqueda> encontrarLasBusquedasEntreDosFechasYPorTextoBuscado(String textoBuscado, LocalDate fechaDeInicio,
			LocalDate fechaDeFin);

	public List<Busqueda> encontrarLasBusquedasQueSeRealizaronEnUnaFecha(LocalDate unaFecha);

	public void eliminarBusqueda(Busqueda unaBusqueda);
	
	public List<Busqueda> encontrarLasBusquedasDeUnaTerminal(String nombreDeTerminal);
	
	public List<Busqueda> encontrarLasBusquedasPorCantidadDeResultados(int cantidadDeResultados);
	
	public Busqueda encontrarBusquedaPorId(String id);
	
	public Busqueda encontrarBusquedaPorId(ObjectId id);

}