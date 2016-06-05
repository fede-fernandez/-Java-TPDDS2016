package ar.edu.dds.tpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.*;

public class AdministradorABMDePOITest {
	Administrador administrador;
	Mapa mapa;
	Terminal terminalFlores;
	Terminal terminalAbasto;
	ParadaDeColectivo paradaDel7;
	Banco bancoSantanderRio;

	@Before
	public void inicializar() {
		administrador = new Administrador(null);
		mapa = new Mapa();
		administrador.setMapa(mapa);
		terminalFlores = new Terminal("Terminal Flores", null);
		terminalAbasto = new Terminal("Terminal Abasto", null);
		paradaDel7 = new ParadaDeColectivo("parada del colectivo linea 7", null);
		bancoSantanderRio = new Banco("Banco Santander Rio Sucursal Ramos Mejia", null);
		administrador.agregarTerminal(terminalFlores);
		administrador.agregarTerminal(terminalAbasto);
		administrador.agregarMapaATerminales();
	}
	
	@Test
	public void altaDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(bancoSantanderRio);
		
		Assert.assertTrue(terminalFlores.buscarPorTextoLibre("santander").contains(bancoSantanderRio));
	}
	
	@Test
	public void bajaDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(paradaDel7);
		administrador.sacarPuntoDeInteres(paradaDel7);
		
		Assert.assertFalse(terminalFlores.buscarPorTextoLibre("7").contains(paradaDel7));
	}
	
	@Test
	public void modificacionDePuntoDeInteres() {
		administrador.agregarPuntoDeInteres(paradaDel7);
		ParadaDeColectivo paradaDel7Modificada = new ParadaDeColectivo("terminal de bondi 7", null);
		administrador.modificarPuntoDeInteres(paradaDel7, paradaDel7Modificada);
		
		Assert.assertTrue(terminalFlores.buscarPorTextoLibre("bondi").contains(paradaDel7Modificada) &&
				!terminalFlores.buscarPorTextoLibre("colectivo").contains(paradaDel7));
	}
	



}