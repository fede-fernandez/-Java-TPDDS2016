package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.BancoServiceAdapterImpostor;
import ar.edu.dds.tpa.adapter.CGPServiceAdapterImpostor;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.service.BancoBuscadorExternoService;
import ar.edu.dds.tpa.service.BancoServiceImpostor;
import ar.edu.dds.tpa.service.CGPBuscadorExternoService;
import ar.edu.dds.tpa.service.CGPServiceImpostor;

public class BusquedaDePOIsConServiciosExternosTest {
	BancoBuscadorExternoService bancoBuscadorExternoService;
	BancoServiceImpostor bancoServiceImpostor;
	BancoServiceAdapterImpostor bancoServiceAdapterImpostor;

	CGPBuscadorExternoService cgpBuscadorExternoService;
	CGPServiceImpostor cgpServiceImpostor;
	CGPServiceAdapterImpostor cgpServiceAdapterImpostor;

	Administrador administrador;
	Mapa mapa;
	Terminal terminalFlores;

	@Before
	public void inicializar() {
		administrador = new Administrador(null);
		mapa = new Mapa();
		administrador.setMapa(mapa);
		terminalFlores = new Terminal("Terminal Flores", null);
		administrador.agregarTerminal(terminalFlores);
		administrador.agregarMapaATerminales();

		bancoServiceImpostor = new BancoServiceImpostor();
		bancoServiceAdapterImpostor = new BancoServiceAdapterImpostor();
		bancoBuscadorExternoService = new BancoBuscadorExternoService(bancoServiceImpostor,
				bancoServiceAdapterImpostor);

		cgpServiceImpostor = new CGPServiceImpostor();
		cgpServiceAdapterImpostor = new CGPServiceAdapterImpostor();
		cgpBuscadorExternoService = new CGPBuscadorExternoService(cgpServiceImpostor, cgpServiceAdapterImpostor);

		mapa.agregarBuscadorExterno(bancoBuscadorExternoService);
		mapa.agregarBuscadorExterno(cgpBuscadorExternoService);
	}

	@Test
	public void seLlamoAlServicioDeBancosPorqueNoSeEncontroLocalmente() {
		terminalFlores.buscarPorTextoLibre("Banco que no existe localmente");
		Assert.assertTrue(bancoServiceImpostor.seLlamoAlBancoService()
				&& bancoServiceAdapterImpostor.seLlamoAlBancoServiceAdapter());
	}

	@Test
	public void seLlamoAlServicioDeCGPSPorqueNoSeEncontroLocalmente() {
		terminalFlores.buscarPorTextoLibre("CGP que no existe localmente");
		Assert.assertTrue(
				cgpServiceImpostor.seLlamoAlCGPService() && cgpServiceAdapterImpostor.seLlamoAlCGPServiceAdapter());
	}
}