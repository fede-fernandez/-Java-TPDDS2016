package ar.edu.dds.tpa.model;

public class ObserverTiempoDeDemora implements ObserverBusquedas{
	
	private long tiempoMaximoDeDemora;
	
	public ObserverTiempoDeDemora(long tiempoMaximoDeDemora){
		this.tiempoMaximoDeDemora = tiempoMaximoDeDemora;
	}	
	
	
	public void informar(Busqueda busqueda){
		if (busqueda.getTiempoDeRespuesta() > tiempoMaximoDeDemora) {
			//enviarMailAlAdministrador();
		}
	}
		

}
