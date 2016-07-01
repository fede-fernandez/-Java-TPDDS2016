package componentes_externos;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Servicio;

public class BancoDTO implements PuntosDTO {

	private String banco;
	private double x;
	private double y;
	private String sucursal;
	private String gerente;
	private List<String> servicios = new ArrayList<String>();
	
	public String getNombre() {
		return banco;
	}


	public void setNombre(String nombre) {
		this.banco = nombre;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public String getSucursal() {
		return sucursal;
	}


	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}


	public String getGerente() {
		return gerente;
	}


	public void setGerente(String gerente) {
		this.gerente = gerente;
	}


	public List<String> getServicios() {
		return servicios;
	}


	public void addServicio(String servicio) {
		this.servicios.add(servicio);
	}
	
	@Override
	public PuntoDeInteres toPuntoDeInteres() {
		Banco banco = new Banco(getNombre(), new Point(getX(), getY()));
		servicios.stream().map(servicio -> new Servicio(servicio)).forEach(banco::agregarServicio);
		return banco;
		
	}

}
