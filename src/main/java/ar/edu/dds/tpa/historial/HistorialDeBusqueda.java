package ar.edu.dds.tpa.historial;

import java.time.LocalDate;
import java.util.List;

import ar.edu.dds.tpa.model.Busqueda;

public interface HistorialDeBusqueda {

	void registrarBusqueda(Busqueda busqueda);

	List<Busqueda> traerTodasLasBusquedas();

	List<Busqueda> encontrarTodasLasBusquedasPorTextoBuscado(String unTexto);

	List<Busqueda> encontrarLasBusquedasEntreDosFechas(LocalDate fechaDeInicio, LocalDate fechaDeFin);

	List<Busqueda> encontrarLasBusquedasEntreDosFechasYPorTextoBuscado(String textoBuscado, LocalDate fechaDeInicio,
			LocalDate fechaDeFin);

	List<Busqueda> encontrarLasBusquedasQueSeRealizaronEnUnaFecha(LocalDate unaFecha);

	void eliminarBusqueda(Busqueda unaBusqueda);

}