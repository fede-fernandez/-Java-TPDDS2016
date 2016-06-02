package ar.edu.dds.tpa.adapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Servicio;

public class BancoServiceAdapter {
	public List<Banco> obtenerBancosDeJSON(String bancosEnJSON) {
		List<Banco> bancos = new ArrayList<Banco>();
		Type listaDeBancos = new TypeToken<List<BancoDelServicio>>() {}.getType();
		Gson gson = new Gson();
		gson.fromJson(bancosEnJSON, listaDeBancos);
		
		return bancos;
	}
	
	public Banco parsearBancoDelServicioABanco (BancoDelServicio bancoDelServicio) {
		Banco banco = new Banco(bancoDelServicio.getBanco(), new Posicion(bancoDelServicio.getX(), bancoDelServicio.getY()));
		bancoDelServicio.getServicios().stream().forEach(unServicio -> banco.agregarServicio(new Servicio(unServicio)));
		return banco;
	}
}

class BancoDelServicio {
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