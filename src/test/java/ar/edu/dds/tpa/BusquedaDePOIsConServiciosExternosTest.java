package ar.edu.dds.tpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.service.BancoServiceImpostor;
import ar.edu.dds.tpa.service.CGPServiceImpostor;

public class BusquedaDePOIsConServiciosExternosTest {
	private BancoServiceImpostor bancoServiceImpostor;
	private CGPServiceImpostor cgpServiceImpostor;
	private Buscador buscador;
	private Mapa mapa;
	private List<PuntoDeInteres> resultadosDeLaBusqueda;

	@Before
	public void inicializar() {
		bancoServiceImpostor = new BancoServiceImpostor(false);

		cgpServiceImpostor = new CGPServiceImpostor(false);

		mapa = new Mapa();

		buscador = new Buscador(mapa);
		buscador.agregarBuscadorExterno(bancoServiceImpostor);
		buscador.agregarBuscadorExterno(cgpServiceImpostor);

		resultadosDeLaBusqueda = new ArrayList<PuntoDeInteres>();
	}

	@Test
	public void seLlamoAlServicioDeBancos() {
		buscador.buscar("Banco de la Plaza", null);

		Assert.assertTrue(bancoServiceImpostor.seLlamoAlBancoService());
	}

	@Test
	public void seObtuvoUnBancoDelServicioExterno() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("Banco de la Plaza", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getNombre().equals("Banco de la Plaza")));
	}

	@Test
	public void seObtuvoElBancoDeSucursalAvellanedaDelServicioExternoDeBancos() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("cobro", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9338322));
	}

	@Test
	public void seObtuvoElBancoDeSucursalCaballitoDelServicioExternoDeBancos() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("seguros", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9345681));
	}

	@Test
	public void seLlamoAlServicioDeCGPSExternos() {
		buscador.buscar("Flores", null);

		Assert.assertTrue(cgpServiceImpostor.seLlamoAlCGPService());
	}

	@Test
	public void seObtuvoElCGPDelServicioExterno() {
		buscador.buscar("Flores", null);

		Assert.assertTrue(resultadosDeLaBusqueda.stream().allMatch(
				unResultado -> unResultado.getNombre().equals("Centros de Gestion y Participacion CGP N° 7")));
	}
}