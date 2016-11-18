package ar.edu.dds.tpa.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.service.BancoServiceImpostor;

public class PersistenciaDeServiciosEnRedisRealTest {
	private BancoServiceImpostor bancoServiceConCacheMock;
	private BancoServiceImpostor bancoServiceSinCacheMock;
	private Buscador buscador;
	private List<PuntoDeInteres> resultadosDeLaBusqueda;

	@Before
	public void inicializar() {
		bancoServiceConCacheMock = new BancoServiceImpostor().conSoporteCache(Redis.obtenerConexion());
		bancoServiceSinCacheMock = new BancoServiceImpostor();
		buscador = new Buscador(new MapaEnMemoria());
		buscador.agregarBuscadorExterno(bancoServiceConCacheMock);
		buscador.agregarBuscadorExterno(bancoServiceSinCacheMock);
		resultadosDeLaBusqueda = new ArrayList<PuntoDeInteres>();
	}

	@Ignore
	@Test
	public void seLlamoUnaSolaVezAlServicioExternoYDespuesSeUsoLaCache() {
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		Assert.assertEquals(1, bancoServiceConCacheMock.getVecesQueSeLlamoAlServicio());
		Assert.assertNotEquals(1, bancoServiceSinCacheMock.getVecesQueSeLlamoAlServicio());
	}

	@Ignore
	@Test
	public void seLlamoOchoVecesAlServicioExternoPorqueSeBuscoPorNombreYServicio() {
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		buscador.buscar("Banco", null);
		Assert.assertEquals(8, bancoServiceSinCacheMock.getVecesQueSeLlamoAlServicio());
	}

	@Ignore
	@Test
	public void seObtuvoUnBancoDelServicioExternoPorCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("Banco de la Plaza", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getNombre().equals("Banco de la Plaza")));
	}

	@Ignore
	@Test
	public void seObtuvoElBancoDeSucursalAvellanedaDelServicioExternoDeBancosPorCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("cobro", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9338322));
	}

	@Ignore
	@Test
	public void seObtuvoElBancoDeSucursalCaballitoDelServicioExternoDeBancosPorCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("seguros", null));

		Assert.assertTrue(resultadosDeLaBusqueda.stream()
				.anyMatch(unResultado -> unResultado.getCoordenadas().getLongitud() == -35.9345681));
	}

	@Ignore
	@Test
	public void seObtuvieronTodosLosBancosDelServicioEnCache() {
		resultadosDeLaBusqueda.addAll(buscador.buscar("banco", null));

		Assert.assertEquals(10, resultadosDeLaBusqueda.size());
	}

	@Ignore
	@Test
	public void seBuscanVariasCosasYSeConsultaALaCache() {
		buscador.buscar("Banco", null);
		buscador.buscar("Provincia", null);
		buscador.buscar("SEGUROS", null);
		buscador.buscar("cheques", null);
		buscador.buscar("Banco Ciudad", null);
		buscador.buscar("santander", null);

		Assert.assertEquals(1, bancoServiceConCacheMock.getVecesQueSeLlamoAlServicio());
	}

	@Ignore
	@Test
	public void seDesactivaLaCacheYSeConsultaAlServicioVariasVeces() {
		bancoServiceConCacheMock.desactivarCache();
		buscador.buscar("Banco", null);
		buscador.buscar("Provincia", null);

		Assert.assertEquals(5, bancoServiceConCacheMock.getVecesQueSeLlamoAlServicio());
	}
}