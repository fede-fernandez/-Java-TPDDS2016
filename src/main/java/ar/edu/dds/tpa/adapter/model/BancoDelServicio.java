package ar.edu.dds.tpa.adapter.model;

import java.util.List;

public class BancoDelServicio {
	private String banco;
	private double x;
	private double y;
	private String sucursal;
	private String gerente;
	private List<String> servicios;

	public String getBanco() {
		return banco;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getSucursal() {
		return sucursal;
	}

	public String getGerente() {
		return gerente;
	}

	public List<String> getServicios() {
		return servicios;
	}
}
