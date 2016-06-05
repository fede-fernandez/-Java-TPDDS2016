package ar.edu.dds.tpa.service;

public class BancoServiceImpostor implements BancoService {
	private boolean seLlamoAlBancoService = false;

	@Override
	public String getSucursalesBancosByNombreBanco(String nombreBanco) {
		seLlamoAlBancoService = true;
		return null;
	}
	
	public boolean seLlamoAlBancoService() {
		return seLlamoAlBancoService;
	}
}