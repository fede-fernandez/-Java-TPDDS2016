package ar.edu.dds.tpa.model;


import java.time.LocalDateTime;




public class Busqueda {
	
	
	private String fraseBuscada;
	private int cantidadDeResultados;
	//private  tiempoDeRespuesta;
	private LocalDateTime fechaDeBusqueda;
	
	public Busqueda(String fraseBuscada, int cantidadDeResultados, LocalDateTime fechaDeBusqueda) {
		this.fraseBuscada = fraseBuscada;
		this.cantidadDeResultados = cantidadDeResultados;
		//this.tiempoDeRespuesta = tiempoDeRespuesta;
		this.fechaDeBusqueda = fechaDeBusqueda;
	}


	
	
	

}
