package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.edu.dds.tpa.model.*;
import componentes_externos.BuscadorDeBancos_Impostor;
import componentes_externos.BuscadorDeCGPLento_Impostor;
import componentes_externos.CentroDTO;
import componentes_externos.RangosServicioDTO;
import componentes_externos.ServicioDTO;

public class BusquedaDePuntoTest {

	// Busqueda de puntos de interes
	
	private Banco unBanco;
	
	private CGP unCGP;
	
	private LocalComercial unLocal;
	private KioscoDeDiario kioscoDeDario;
	
	private LocalComercial unLocal2;
	private LibreriaEscolar libreriaEscolar;


	private ParadaDeColectivo un144_1;
	private ParadaDeColectivo un144_2;
	private ParadaDeColectivo un144_3;
	
	private Mapa mapa;
	
	private List<PuntoDeInteres> puntosDeInteresEncontrados;
	
	private BuscadorDeCGPLento_Impostor buscadorDeCGPLento;

	private CentroDTO centroDTO;
	private CentroDTO otroCentroDTO;

	private ServicioDTO servicioDTO;
	
	private BuscadorDeBancos_Impostor buscadorDeBancos;
	
	@Before
	public void initialize(){
		
		unBanco = new Banco("Ciudad", null);
		
		unCGP = new CGP("CGP Balbanera", null, null);
		unCGP.agregarServicio(new Servicio("Rentas"));
		
		kioscoDeDario = new KioscoDeDiario("Kiosco De Diarios");
		unLocal = new LocalComercial("El matutino", null, kioscoDeDario);
		
		libreriaEscolar = new LibreriaEscolar("libreria escolar");
		unLocal2 = new LocalComercial("El ateneo", null, libreriaEscolar);
		
		un144_1 = new ParadaDeColectivo("144", null);
		un144_2 = new ParadaDeColectivo("144", null);
		un144_3 = new ParadaDeColectivo("144", null);
		
		mapa = new Mapa();
		
		mapa.agregarPuntoInteres(un144_1);
		mapa.agregarPuntoInteres(un144_2);
		mapa.agregarPuntoInteres(un144_3);
		mapa.agregarPuntoInteres(unLocal);
		mapa.agregarPuntoInteres(unLocal2);
		mapa.agregarPuntoInteres(unBanco);
		mapa.agregarPuntoInteres(unCGP);
	
		puntosDeInteresEncontrados = new ArrayList<>();
		
		
		
		servicioDTO = new ServicioDTO();
		servicioDTO.addRangoServicioDTO(new RangosServicioDTO(1,1,1,1,1));
		
		centroDTO = new CentroDTO();
		centroDTO.setComuna(1);
		centroDTO.setZona("Zona de prueba");
		centroDTO.setDirector("Don Pepito");
		centroDTO.setDomicilioCompleto("Calle Falsa 123");
		centroDTO.setTelefono("555-5555");
		centroDTO.addServiciosDTO(servicioDTO);
		
		otroCentroDTO = new CentroDTO();
		otroCentroDTO.setComuna(2);
		otroCentroDTO.setZona("Zona cheta");
		otroCentroDTO.setDirector("Rolando Mota");
		otroCentroDTO.setDomicilioCompleto("Calle Posta 321");
		otroCentroDTO.setTelefono("555-5555");
		otroCentroDTO.addServiciosDTO(servicioDTO);
		
		
		buscadorDeCGPLento = new BuscadorDeCGPLento_Impostor();
		buscadorDeCGPLento.agregarCentro(centroDTO);
		buscadorDeCGPLento.agregarCentro(otroCentroDTO);
		
		
		
		mapa.agregarBuscadorExterno(buscadorDeCGPLento);
		
		String jsonBancos = "[\n" + 
				"		                     { \"banco\": \"Banco de la Plaza\",\n" + 
				"		                         \"x\": -35.9338322,\n" + 
				"		                         \"y\": 72.348353,\n" + 
				"		                         \"sucursal\": \"Avellaneda\",\n" + 
				"		                         \"gerente\": \"Javier Loeschbor\",\n" + 
				"		                         \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]\n" + 
				"		                      },\n" + 
				"		                      { \"banco\": \"Banco de la Plaza\",\n" + 
				"		                         \"x\": -35.9345681,\n" + 
				"		                         \"y\": 72.344546,\n" + 
				"		                         \"sucursal\": \"Caballito\",\n" + 
				"		                         \"gerente\": \"Fabián Fantaguzzi\",\n" + 
				"		                         \"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\" ]\n" + 
				"		                      }\n" + 
				"		                   ]";
		buscadorDeBancos = new BuscadorDeBancos_Impostor();
		buscadorDeBancos.parsearJSON(jsonBancos);
		
		mapa.agregarBuscadorExterno(buscadorDeBancos);
	}
	
	
	@Test
	public void busqueda_de_todos_los_144(){

		puntosDeInteresEncontrados.add(un144_1);
		puntosDeInteresEncontrados.add(un144_2);
		puntosDeInteresEncontrados.add(un144_3);
		
		Assert.assertEquals(puntosDeInteresEncontrados, mapa.buscarPorTextoLibre("144"));
	}
	
	@Test
	public void busqueda_por_rubro(){

		puntosDeInteresEncontrados.add(unLocal);
		
		Assert.assertEquals(puntosDeInteresEncontrados, mapa.buscarPorTextoLibre("Kiosco De Diarios"));
	}
	
	@Test
	public void busqueda_por_rubro_de_una_libreria(){

		puntosDeInteresEncontrados.add(unLocal2);
		
		Assert.assertEquals(puntosDeInteresEncontrados, mapa.buscarPorTextoLibre("libreria escolar"));
	}
	
	
	@Test
	public void busqueda_por_parte_de_un_servicio_CGP(){

		puntosDeInteresEncontrados.add(unCGP);
		
		Assert.assertEquals(puntosDeInteresEncontrados, mapa.buscarPorTextoLibre("Rentas"));	
	}
	
	

	
	@Test
	public void busqueda_de_nombre_inexistente(){
		Assert.assertEquals(puntosDeInteresEncontrados, mapa.buscarPorTextoLibre("un nombre inexistente"));
		
	}
	
	@Test
	public void busqueda_de_cgp_en_buscador_externo_lento_con_resultado() throws Exception {
		mapa.buscarPorTextoLibre("1");
		Assert.assertTrue(mapa.getResultadosExternos().contains(centroDTO.toPuntoDeInteres()));
	}
	
	@Test
	public void busqueda_de_cgp_en_buscador_externo_lento_sin_resultado() throws Exception {
		mapa.buscarPorTextoLibre("1000"); //No hay cgp con comuna 1000
		Assert.assertTrue(mapa.getResultadosExternos().isEmpty());
	}
	
	@Test
	public void busqueda_de_cgp_en_buscador_externo_lento_devuelve_un_solo_resultado() throws Exception {
		mapa.buscarPorTextoLibre("1");
		Assert.assertTrue(mapa.getResultadosExternos().size()==1);
	}
	
	@Test
	public void busqueda_de_banco_en_buscador_externo_con_resultado() throws Exception {
		mapa.buscarPorTextoLibre("Banco plaza");
		Assert.assertTrue(mapa.getResultadosExternos().contains(new Banco("Banco de la Plaza", new Point(-35.9338322, 72.348353))));
	}
	
	@Test
	public void busqueda_de_banco_en_buscador_externo_sin_resultado() throws Exception {
		mapa.buscarPorTextoLibre("inexistente");
		Assert.assertTrue(mapa.getResultadosExternos().isEmpty());
	}
	
	@Test
	public void busqueda_de_banco_en_buscador_externo_devuelve_dos_resultados() throws Exception {
		mapa.buscarPorTextoLibre("Banco plaza");
		Assert.assertTrue(mapa.getResultadosExternos().size()==2);
	}
	
	@Test
	public void busqueda_en_buscador_externo_devuelve_dos_bancos_y_un_cgp() throws Exception {
		mapa.buscarPorTextoLibre("Banco plaza 2");
		Assert.assertTrue(mapa.getResultadosExternos().size()==3 &&  //Tiene 3 elementos
							mapa.getResultadosExternos().contains(otroCentroDTO.toPuntoDeInteres()) && //Uno es otroCentroDTO
							mapa.getResultadosExternos().stream().filter(punto -> punto.getClass()==Banco.class).collect(Collectors.toList()).size()==2); //Los otros dos son del tipo banco
	}
}
