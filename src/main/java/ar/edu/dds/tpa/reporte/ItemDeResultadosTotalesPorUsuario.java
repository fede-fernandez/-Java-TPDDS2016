package ar.edu.dds.tpa.reporte;

public class ItemDeResultadosTotalesPorUsuario{
	
	private String usuario;
	
	private int cantidadDeResultadosTotales;

	public ItemDeResultadosTotalesPorUsuario(String usuario, int cantidadDeResultadosTotales) {
		this.usuario = usuario;
		this.cantidadDeResultadosTotales = cantidadDeResultadosTotales;
	}

	public String getUsuario() {
		return usuario;
	}

	public int getCantidadDeResultadosTotales() {
		return cantidadDeResultadosTotales;
	}
}
