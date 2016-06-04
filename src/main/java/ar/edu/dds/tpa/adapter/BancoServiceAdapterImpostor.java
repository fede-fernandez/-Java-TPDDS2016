package ar.edu.dds.tpa.adapter;

import java.util.List;

import ar.edu.dds.tpa.model.Banco;

public class BancoServiceAdapterImpostor implements BancoServiceAdapter {
	private boolean seLlamoAlBancoServiceAdapter = false;
	
	@Override
	public List<Banco> obtenerBancosDeJSON(String bancosEnJSON) {
		seLlamoAlBancoServiceAdapter = true;
		return null;
	}

	public boolean seLlamoAlBancoServiceAdapter() {
		return seLlamoAlBancoServiceAdapter;
	}
}