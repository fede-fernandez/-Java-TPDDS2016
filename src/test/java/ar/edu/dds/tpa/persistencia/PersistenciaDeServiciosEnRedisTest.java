package ar.edu.dds.tpa.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.service.BancoServiceImpostor;

public class PersistenciaDeServiciosEnRedisTest {
	private BancoServiceImpostor bancoServiceConCacheMock;
	private BancoServiceImpostor bancoServiceSinCacheMock;
	private Buscador buscador;
	private Fedis cache;
	private List<PuntoDeInteres> resultadosDeLaBusqueda;
	
	@Before
	public void inicializar() {
		cache = new Fedis();
		bancoServiceConCacheMock = new BancoServiceImpostor().conSoporteCache(cache);
		bancoServiceSinCacheMock = new BancoServiceImpostor();
		buscador = new Buscador(new Mapa());
		buscador.agregarBuscadorExterno(bancoServiceConCacheMock);
		buscador.agregarBuscadorExterno(bancoServiceSinCacheMock);
		resultadosDeLaBusqueda = new ArrayList<PuntoDeInteres>();
	}

	@Test
	public void seLlamoUnaSolaVezAlServicioExternoYDespuesSeUsoLaCache() {
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		Assert.assertEquals(1, bancoServiceConCacheMock.getVecesQueSeLlamoAlServicio());
		Assert.assertNotEquals(1, bancoServiceSinCacheMock.getVecesQueSeLlamoAlServicio());
	}

	@Test
	public void seObtuvoUnBancoDelServicioExternoPorCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("Banco de la Plaza", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getNombre().equals("Banco de la Plaza")));
	}

	@Test
	public void seObtuvoElBancoDeSucursalAvellanedaDelServicioExternoDeBancosPorCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("cobro", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9338322));
	}

	@Test
	public void seObtuvoElBancoDeSucursalCaballitoDelServicioExternoDeBancosPorCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("seguros", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9345681));
	}
	
	@Test
	public void seObtuvieronTodosLosBancosDelServicioEnCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("banco", null));
		
		Assert.assertEquals(10, resultadosDeLaBusqueda.size());
	}
}