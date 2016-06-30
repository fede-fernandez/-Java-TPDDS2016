package ar.edu.dds.tpa;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.DarDeBajaPuntosDeInteres;
import ar.edu.dds.tpa.model.KioscoDeDiario;
import ar.edu.dds.tpa.model.LibreriaEscolar;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.model.ServicioPuntosDeInteresBajados;
import ar.edu.dds.tpa.model.ServicioPuntosDeInteresBajados_Impostor;
import componentes_externos.BuscadorDeBancos_Impostor;
import componentes_externos.BuscadorDeCGPLento_Impostor;
import componentes_externos.CentroDTO;
import componentes_externos.RangosServicioDTO;
import componentes_externos.ServicioDTO;

public class DarDeBajaPuntosDeInteresTest {

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
	
	private DarDeBajaPuntosDeInteres bajaPuntosDeInteres;
	ServicioPuntosDeInteresBajados_Impostor servicioBajas;
	
	@Before
	public void initialize(){
		
		unBanco = new Banco("Ciudad", new Point(10,10));
		
		unCGP = new CGP("CGP Balbanera", new Point(-1, -1), null);
		unCGP.agregarServicio(new Servicio("Rentas"));
		
		kioscoDeDario = new KioscoDeDiario("Kiosco De Diarios");
		unLocal = new LocalComercial("El matutino", new Point(2,2), kioscoDeDario);
		
		libreriaEscolar = new LibreriaEscolar("libreria escolar");
		unLocal2 = new LocalComercial("El ateneo", new Point(0,0), libreriaEscolar);
		
		un144_1 = new ParadaDeColectivo("144", new Point(-1, -1));
		un144_2 = new ParadaDeColectivo("144", new Point(0, 0));
		un144_3 = new ParadaDeColectivo("144", new Point(1, 1));
		
		mapa = new Mapa();
		
		mapa.agregarPuntoInteres(un144_1);
		mapa.agregarPuntoInteres(un144_2);
		mapa.agregarPuntoInteres(un144_3);
		mapa.agregarPuntoInteres(unLocal);
		mapa.agregarPuntoInteres(unLocal2);
		mapa.agregarPuntoInteres(unBanco);
		mapa.agregarPuntoInteres(unCGP);
	
		puntosDeInteresEncontrados = new ArrayList<>();
		
		
	
		
		String jsonDarDeBaja = "[{\"nombre\" : \"144\",\"x\" : 1,\"y\" : 1,\"dia\" : 30,\"mes\" : 6,\"anio\" : 2016}, {\"nombre\" : \"El_ateneo\",\"x\" : 100,\"y\" : 100,\"dia\" : 30,\"mes\" : 6,\"anio\" : 2016}, {\"nombre\" : \"Mal_nombre\",\"x\" : 2,\"y\" : 2,\"dia\" : 30,\"mes\" : 6,\"anio\" : 2016}]";
		
		
		
		bajaPuntosDeInteres = new DarDeBajaPuntosDeInteres();
		bajaPuntosDeInteres.setMapa(mapa);
		
		servicioBajas = new ServicioPuntosDeInteresBajados_Impostor();
		servicioBajas.setPuntosDeInteresJSON(jsonDarDeBaja);
		
		bajaPuntosDeInteres.setServicioBajas(servicioBajas);
		
	}

	@Test
	public void seLlamaAlServicio() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		assertTrue(servicioBajas.getFueLlamado());
	}
	
	@Test
	public void seDaDeBajaSoloLaParadaDel114ConCoordenadas_x1y1() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		assertFalse(mapa.buscarPorTextoLibre("144").contains(un144_3));		
	}
	
	@Test
	public void noSeDaDeBajaAElAteneo() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		assertTrue(mapa.buscarPorTextoLibre("El Ateneo").contains(unLocal2));
	}
	
	@Test
	public void noSeDioDeBajaElKioscoConCoordenadas_x2y2() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		assertTrue(mapa.buscarPorTextoLibre("matutino").contains(unLocal));
	}
}
