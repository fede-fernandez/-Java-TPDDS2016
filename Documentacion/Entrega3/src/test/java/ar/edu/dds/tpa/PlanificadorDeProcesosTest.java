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
		servicioDeMailImpostor = new MailServiceImpostor();
		enviadorDeMail = new EnviadorDeMail(servicioDeMailImpostor);
		administrador = new Administrador("mailDelAdministrador@puntosDeInteres.com.ar");
	}
	
	@Test
	public void seEjecutoElProcesoPlanificadoQueSeEjecuteEnElMomento() throws InterruptedException {
		procesoImpostor = new ProcesoImpostor();
		planificador.planificar(procesoImpostor, LocalDateTime.now());
		Thread.sleep(50);
		
		Assert.assertTrue(procesoImpostor.seLlamoAlProcesoImpostor());
	}
	
	@Test
	public void noSeEjecutoElProcesoPlanificadoAlHorarioDadoPorqueTodaviaNoEsHora() throws InterruptedException {
		procesoImpostor = new ProcesoImpostor();
		planificador.planificar(procesoImpostor, LocalDateTime.now().plus(1, ChronoUnit.HOURS));
		Thread.sleep(10);
		
		Assert.assertFalse(procesoImpostor.seLlamoAlProcesoImpostor());
	}
	
	@Test
	public void falloElProcesoYSeReintentaTresVecesYNoSeNotificaDespues() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		reintentarEjecucion = new ReintentarEjecucion(procesoQueFallaImpostor, 3, false);
		procesoQueFallaImpostor.enCasoDeFalloHacer(reintentarEjecucion);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		Thread.sleep(50);
		
		Assert.assertEquals(4, procesoQueFallaImpostor.vecesQueSeEjecuto());
	}
	
	@Test
	public void falloElProcesoYSeReintentaTresVecesYSeNotificaAlAdministradorDespues() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		reintentarEjecucion = new ReintentarEjecucion(procesoQueFallaImpostor, 3, true);
		reintentarEjecucion.administradorANotificar(administrador, enviadorDeMail);
		procesoQueFallaImpostor.enCasoDeFalloHacer(reintentarEjecucion);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		Thread.sleep(50);
		
		Assert.assertTrue(servicioDeMailImpostor.seLlamoAlServicioDeEnvioDeMail());
	}
	
	@Test
	public void falloElProcesoYNoSeRealizaNada() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		procesoQueFallaImpostor.enCasoDeFalloHacer(noHacerNada);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		Thread.sleep(50);
		
		Assert.assertEquals(1, procesoQueFallaImpostor.vecesQueSeEjecuto());
	}
	
	@Test
	public void resultadoDeEjecucionDeUnProcesoEstado() throws InterruptedException {
		procesoImpostor = new ProcesoImpostor();
		planificador.planificar(procesoImpostor, LocalDateTime.now().plus(1, ChronoUnit.HOURS));
		procesoImpostor.finalizar(2);
		Thread.sleep(50);
		
		Assert.assertTrue(procesoImpostor.obtenerResultadoDeEjecucion().seFinalizoElProceso());
	}
	
	@Test
	public void resultadoDeEjecucionDeUnProcesoCantidadDeElementosAfectados() throws InterruptedException {
		procesoImpostor = new ProcesoImpostor();
		planificador.planificar(procesoImpostor, LocalDateTime.now().plus(1, ChronoUnit.HOURS));
		procesoImpostor.finalizar(2);
		Thread.sleep(50);
		
		Assert.assertEquals(2, procesoImpostor.obtenerResultadoDeEjecucion().getCantidadDeElementosAfectados());
	}
	
	@Test
	public void resultadoDeEjecucionDeUnProcesoQueFallaEstado() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		procesoQueFallaImpostor.enCasoDeFalloHacer(noHacerNada);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		procesoQueFallaImpostor.finalizar(0);
		Thread.sleep(50);
		
		Assert.assertTrue(procesoQueFallaImpostor.obtenerResultadoDeEjecucion().seFinalizoElProceso());
	}
		
	@Test
	public void resultadoDeEjecucionDeUnProcesoQueFallaCantidadDeElementosAfectados() throws InterruptedException {
		procesoQueFallaImpostor = new ProcesoQueFallaImpostor();
		procesoQueFallaImpostor.enCasoDeFalloHacer(noHacerNada);		
		planificador.planificar(procesoQueFallaImpostor, LocalDateTime.now());
		procesoQueFallaImpostor.finalizar(0);
		Thread.sleep(50);
		
		Assert.assertEquals(0, procesoQueFallaImpostor.obtenerResultadoDeEjecucion().getCantidadDeElementosAfectados());
	}
}