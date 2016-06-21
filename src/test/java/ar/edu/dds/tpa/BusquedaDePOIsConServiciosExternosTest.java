package ar.edu.dds.tpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.BancoServiceAdapterReal;
import ar.edu.dds.tpa.adapter.CGPServiceAdapterReal;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Terminal;
import ar.edu.dds.tpa.service.BancoBuscadorExternoService;
import ar.edu.dds.tpa.service.BancoServiceImpostor;
import ar.edu.dds.tpa.service.CGPBuscadorExternoService;
import ar.edu.dds.tpa.service.CGPServiceImpostor;

public class BusquedaDePOIsConServiciosExternosTest {
	BancoBuscadorExternoService bancoBuscadorExternoService;
	BancoServiceImpostor bancoServiceImpostor;
	BancoServiceAdapterReal bancoServiceAdapterReal;

	CGPBuscadorExternoService cgpBuscadorExternoService;
	CGPServiceImpostor cgpServiceImpostor;
	CGPServiceAdapterReal cgpServiceAdapterReal;

	Administrador administrador;
	Mapa mapa;
	Terminal terminalFlores;
	
	List<PuntoDeInteres> resultadosDeLaBusqueda;

	@Before
	public void inicializar() {
		administrador = new Administrador(null);
		mapa = new Mapa();
		administrador.setMapa(mapa);
		terminalFlores = new Terminal("Terminal Flores", null);
		administrador.agregarTerminal(terminalFlores);
		administrador.agregarMapaATerminales();

		bancoServiceImpostor = new BancoServiceImpostor();
		bancoServiceAdapterReal = new BancoServiceAdapterReal();
		bancoBuscadorExternoService = new BancoBuscadorExternoService(bancoServiceImpostor,
				bancoServiceAdapterReal);

		cgpServiceImpostor = new CGPServiceImpostor();
		cgpServiceAdapterReal = new CGPServiceAdapterReal();
		cgpBuscadorExternoService = new CGPBuscadorExternoService(cgpServiceImpostor, cgpServiceAdapterReal);

		mapa.agregarBuscadorExterno(bancoBuscadorExternoService);
		mapa.agregarBuscadorExterno(cgpBuscadorExternoService);
		
		resultadosDeLaBusqueda = new ArrayList<PuntoDeInteres>();
	}

	@Test
	public void seLlamoAlServicioDeBancos() {
		terminalFlores.buscarPorTextoLibre("Banco de la Plaza");
		Assert.assertTrue(bancoServiceImpostor.seLlamoAlBancoService());
	}
	
	@Test
	public void seObtuvoUnBancoDelServicioExterno() {
		resultadosDeLaBusqueda.addAll(terminalFlores.buscarPorTextoLibre("Banco de la Plaza"));
		Assert.assertTrue(resultadosDeLaBusqueda.stream().anyMatch(unResultado -> unResultado.getNombre().equals("Banco de la Plaza")));
	}
	
	@Test
	public void seObtuvoElBancoDeSucursalAvellanedaDelServicioExternoDeBancos() {
		resultadosDeLaBusqueda.addAll(terminalFlores.buscarPorTextoLibre("cobro"));
		Assert.assertTrue(resultadosDeLaBusqueda.stream().anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9338322));
	}
	
	@Test
	public void seObtuvoElBancoDeSucursalCaballitoDelServicioExternoDeBancos() {
		resultadosDeLaBusqueda.addAll(terminalFlores.buscarPorTextoLibre("seguros"));
		Assert.assertTrue(resultadosDeLaBusqueda.stream().anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9345681));
	}

	@Test
	public void seLlamoAlServicioDeCGPSExternos() {
		terminalFlores.buscarPorTextoLibre("Flores");
		Assert.assertTrue(cgpServiceImpostor.seLlamoAlCGPService());
	}
	
	@Test
	public void seObtuvoElCGPDelServicioExterno() {
		terminalFlores.buscarPorTextoLibre("Flores");
		Assert.assertTrue(resultadosDeLaBusqueda.stream().allMatch(unResultado -> unResultado.getNombre().equals("Centros de Gestion y Participacion CGP N° 7")));
	}
}