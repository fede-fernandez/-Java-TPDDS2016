package ar.edu.dds.tpa.adapter;

import java.util.List;

import ar.edu.dds.tpa.model.Banco;

public interface BancoServiceAdapter {
	public List<Banco> obtenerBancosDeJSON(String bancosEnJSON);
}