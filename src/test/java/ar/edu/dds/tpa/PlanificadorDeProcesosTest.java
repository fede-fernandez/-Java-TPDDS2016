package ar.edu.dds.tpa;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.adapter.EnviadorDeMail;
import ar.edu.dds.tpa.model.Administrador;
import ar.edu.dds.tpa.procesos.PlanificadorDeProcesos;
import ar.edu.dds.tpa.procesos.ProcesoImpostor;
import ar.edu.dds.tpa.procesos.ProcesoQueFallaImpostor;
import ar.edu.dds.tpa.procesos.fallos.NoHacerNada;
import ar.edu.dds.tpa.procesos.fallos.NotificarAlAdministrador;
import ar.edu.dds.tpa.procesos.fallos.ReintentarEjecucion;
import ar.edu.dds.tpa.service.MailServiceImpostor;

public class PlanificadorDeProcesosTest {
	PlanificadorDeProcesos planificador;
	ProcesoImpostor procesoImpostor;
	ProcesoQueFallaImpostor procesoQueFallaImpostor;
	NoHacerNada noHacerNada;
	NotificarAlAdministrador notificarAlAdministrador;
	ReintentarEjecucion reintentarEjecucion;
	Administrador administrador;
	EnviadorDeMail enviadorDeMail;
	MailServiceImpostor servicioDeMailImpostor;
	
	@Before
	public void inicializar() {
		planificador = new PlanificadorDeProcesos();
		procesoImpostor = new ProcesoImpostor();
		servicioDeMailImpostor = new MailServiceImpostor();
		enviadorDeMail = new EnviadorDeMail(servicioDeMailImpostor);
		administrador = new Administrador("mailDelAdministrador@puntosDeInteres.com.ar");
	}
	
	@Test
	public void seEjecutoElProcesoPlanificadoQueSeEjecuteEnElMomento() throws InterruptedException {
		planificador.planificar(procesoImpostor, LocalDateTime.now());
		Thread.sleep(5);
		
		Assert.assertTrue(procesoImpostor.seLlamoAlProcesoImpostor());
	}
	
	@Test
	public void noSeEjecutoElProcesoPlanificadoAlHorarioDadoPorqueTodaviaNoEsHora() throws InterruptedException {
		planificador.planificar(procesoImpostor, LocalDateTime.now().plus(1, ChronoUnit.HOURS));
		Thread.sleep(5);
		
		Assert.assertFalse(procesoImpostor.seLlamoAlProcesoImpostor());
	}
	
	@Test
	public void falloElProcesoYSeReintentaTresVecesYNoSeNotificaDespues() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		reintentarEjecucion = new ReintentarEjecucion(procesoQueFallaImpostor, 3, false);
		procesoQueFallaImpostor.enCasoDeFalloHacer(reintentarEjecucion);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		Thread.sleep(5);
		
		Assert.assertEquals(4, procesoQueFallaImpostor.vecesQueSeEjecuto());
	}
	
	@Test
	public void falloElProcesoYSeReintentaTresVecesYSeNotificaAlAdministradorDespues() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		reintentarEjecucion = new ReintentarEjecucion(procesoQueFallaImpostor, 3, true);
		reintentarEjecucion.administradorANotificar(administrador, enviadorDeMail);
		procesoQueFallaImpostor.enCasoDeFalloHacer(reintentarEjecucion);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		Thread.sleep(5);
		
		Assert.assertTrue(servicioDeMailImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
	
	@Test
	public void falloElProcesoYNoSeRealizaNada() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		procesoQueFallaImpostor.enCasoDeFalloHacer(noHacerNada);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		Thread.sleep(5);
		
		Assert.assertEquals(1, procesoQueFallaImpostor.vecesQueSeEjecuto());
	}
}