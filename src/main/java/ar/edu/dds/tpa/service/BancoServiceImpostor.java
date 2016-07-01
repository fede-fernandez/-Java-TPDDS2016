package ar.edu.dds.tpa.service;

public class BancoServiceImpostor implements BancoService {
	private boolean seLlamoAlBancoService = false;

	@Override
	public String getSucursalesBancosByNombreBanco(String nombreBanco) {
		seLlamoAlBancoService = true;
		if(nombreBanco.contains("Banco")) {
			return String.join("\n", 
				"[", 
				"{ \"banco\": \"Banco de la Plaza\",",
				"\"x\": -35.9338322,",
				"\"y\": 72.348353,",
				"\"sucursal\": \"Avellaneda\",",
				"\"gerente\": \"Javier Loeschbor\",",
				"\"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]",
				"},",
				"{ \"banco\": \"Banco de la Plaza\",",
				"\"x\": -35.9345681,",
				"\"y\": 72.344546,",
				"\"sucursal\": \"Caballito\",",
				"\"gerente\": \"Fabián Fantaguzzi\",",
				"\"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\" ]",
				"}",
				"]");
		}
		else {
			return "[]";
		}
	}
	
	@Override
	public String getSucursalesBancosByServicio(String nombreServicio) {
		seLlamoAlBancoService = true;
		if(nombreServicio.contains("cobro")) {
			return String.join("\n", 
				"[", 
				"{ \"banco\": \"Banco de la Plaza\",",
				"\"x\": -35.9338322,",
				"\"y\": 72.348353,",
				"\"sucursal\": \"Avellaneda\",",
				"\"gerente\": \"Javier Loeschbor\",",
				"\"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]",
				"}]");
		}
		else if(nombreServicio.contains("seguros")) {
			return String.join("\n", 
					"[", 
					"{ \"banco\": \"Banco de la Plaza\",",
					"\"x\": -35.9345681,",
					"\"y\": 72.344546,",
					"\"sucursal\": \"Caballito\",",
					"\"gerente\": \"Fabián Fantaguzzi\",",
					"\"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\" ]",
					"}]");
		}
		else {
			return "[]";
		}
	}
	
	public boolean seLlamoAlBancoService() {
		return seLlamoAlBancoService;
	}
}