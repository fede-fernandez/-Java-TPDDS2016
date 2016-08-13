package ar.edu.dds.tpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.geolocalizacion.Posicion;
import ar.edu.dds.tpa.model.Banco;
import ar.edu.dds.tpa.model.Buscador;
import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.LocalComercial;
import ar.edu.dds.tpa.model.Mapa;
import ar.edu.dds.tpa.model.ParadaDeColectivo;
import ar.edu.dds.tpa.model.Rubro;
import ar.edu.dds.tpa.model.Servicio;
import ar.edu.dds.tpa.model.ServicioPuntosDeInteresBajados_Impostor;
import ar.edu.dds.tpa.procesos.DarDeBajaPuntosDeInteres;

public class DarDeBajaPuntosDeInteresTest {

	private Banco unBanco;
	
	private CGP unCGP;
	
	private LocalComercial unLocal;
	
	private LocalComercial unLocal2;


	private ParadaDeColectivo un144_1;
	private ParadaDeColectivo un144_2;
	private ParadaDeColectivo un144_3;
	
	private Mapa mapa;
	
	private Buscador buscador;
	
	
	private DarDeBajaPuntosDeInteres bajaPuntosDeInteres;
	ServicioPuntosDeInteresBajados_Impostor servicioBajas;
	
	@Before
	public void initialize(){
		
		unBanco = new Banco("Ciudad", new Posicion(10,10));
		
		unCGP = new CGP("CGP Balvanera", new Posicion(-1, -1));
		unCGP.agregarServicio(new Servicio("Rentas"));
		
		Rubro kioscoDeDario = new Rubro("Kiosco De Diarios", 2);
		unLocal = new LocalComercial("El matutino", new Posicion(2,2), kioscoDeDario);
		
		Rubro libreriaEscolar = new Rubro("libreria escolar", 3);
		unLocal2 = new LocalComercial("El ateneo", new Posicion(0,0), libreriaEscolar);
		
		un144_1 = new ParadaDeColectivo("144", new Posicion(-1, -1));
		un144_2 = new ParadaDeColectivo("144", new Posicion(0, 0));
		un144_3 = new ParadaDeColectivo("144", new Posicion(1, 1));
		
		mapa = new Mapa();
		
		mapa.agregarPuntoDeInteres(un144_1);
		mapa.agregarPuntoDeInteres(un144_2);
		mapa.agregarPuntoDeInteres(un144_3);
		mapa.agregarPuntoDeInteres(unLocal);
		mapa.agregarPuntoDeInteres(unLocal2);
		mapa.agregarPuntoDeInteres(unBanco);
		mapa.agregarPuntoDeInteres(unCGP);
	
		
		String jsonDarDeBaja = "[{\"nombre\" : \"144\",\"x\" : 1,\"y\" : 1,\"dia\" : 30,\"mes\" : 6,\"anio\" : 2016}, {\"nombre\" : \"El_ateneo\",\"x\" : 100,\"y\" : 100,\"dia\" : 30,\"mes\" : 6,\"anio\" : 2016}, {\"nombre\" : \"Mal_nombre\",\"x\" : 2,\"y\" : 2,\"dia\" : 30,\"mes\" : 6,\"anio\" : 2016}]";
		
		
		
		bajaPuntosDeInteres = new DarDeBajaPuntosDeInteres();
		bajaPuntosDeInteres.setMapa(mapa);
		
		servicioBajas = new ServicioPuntosDeInteresBajados_Impostor();
		servicioBajas.setPuntosDeInteresJSON(jsonDarDeBaja);
		
		bajaPuntosDeInteres.setServicioBajas(servicioBajas);
		
		buscador = new Buscador(mapa);
		
	}

	@Test
	public void seLlamaAlServicio() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		assertTrue(servicioBajas.getFueLlamado());
	}
	
	@Test
	public void seDaDeBajaSoloLaParadaDel114ConCoordenadas_x1y1() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("144");
		assertFalse(buscador.buscarLocalmente(palabras).contains(un144_3));		
	}
	
	@Test
	public void noSeDaDeBajaAElAteneo() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("El");
		palabras.add("Ateneo");
		assertTrue(buscador.buscarLocalmente(palabras).contains(unLocal2));
	}
	
	@Test
	public void noSeDioDeBajaElKioscoConCoordenadas_x2y2() throws Exception {
		bajaPuntosDeInteres.ejecutar();
		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("matutino");
		assertTrue(buscador.buscarLocalmente(palabras).contains(unLocal));
	}
}