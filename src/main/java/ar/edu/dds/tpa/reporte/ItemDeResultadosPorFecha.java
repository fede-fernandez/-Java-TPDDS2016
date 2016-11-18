package ar.edu.dds.tpa.reporte;

import java.time.LocalDate;

public class ItemDeResultadosPorFecha{
	
	private LocalDate fechaDeBusqueda;
	
	private int cantidadDeResultados;

	public ItemDeResultadosPorFecha(LocalDate fechaDeBusqueda, int cantidadDeResultados) {
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.cantidadDeResultados = cantidadDeResultados;
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

}
