package ar.edu.dds.tpa.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class BancoServiceImpostor extends BancoService {
	private boolean seLlamoAlBancoService = false;
	private int vecesQueSeLlamoAlServicio;
	File bancosJSONMock;

	public BancoServiceImpostor() {
		vecesQueSeLlamoAlServicio = 0;
		bancosJSONMock = new File("resources/BancosMockServicio.json");
	}

	@Override
	public String getSucursalesBancosByNombreBanco(String nombreBanco) {
		seLlamoAlBancoService = true;
		vecesQueSeLlamoAlServicio++;
		if (nombreBanco.contains("Banco")) {
			try {
				return FileUtils.readFileToString(bancosJSONMock, StandardCharsets.UTF_8);
			} catch (IOException e) {
				return "[]";
			}
		} else {
			return "[]";
		}
	}

	@Override
	public String getSucursalesBancosByServicio(String nombreServicio) {
		seLlamoAlBancoService = true;
		vecesQueSeLlamoAlServicio++;
		if (nombreServicio.contains("cobro") || nombreServicio.contains("seguro")) {
			try {
				return FileUtils.readFileToString(bancosJSONMock, StandardCharsets.UTF_8);
			} catch (IOException e) {
				return "[]";
			}
		} else {
			return "[]";
		}
	}

	@Override
	public String getSucursalesBancos() {
		vecesQueSeLlamoAlServicio++;
		try {
			return FileUtils.readFileToString(bancosJSONMock, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "[]";
		}
	}

	public boolean seLlamoAlBancoService() {
		return seLlamoAlBancoService;
	}

	public int getVecesQueSeLlamoAlServicio() {
		return vecesQueSeLlamoAlServicio;
	}
}