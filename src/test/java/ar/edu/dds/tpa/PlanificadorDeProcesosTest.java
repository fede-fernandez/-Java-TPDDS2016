package ar.edu.dds.tpa;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.dds.tpa.model.PlanificadorDeProcesos;
import ar.edu.dds.tpa.procesos.ProcesoImpostor;

public class PlanificadorDeProcesosTest {
	PlanificadorDeProcesos planificador;
	ProcesoImpostor procesoImpostor;
	
	@Before
	public void inicializar() {
		planificador = new PlanificadorDeProcesos();
		procesoImpostor = new ProcesoImpostor();
		
	}
	
	@Test
	public void seEjecutoElProcesoPlanificadoQueSeEjecuteEnElMomento() throws InterruptedException {
		planificador.planificar(procesoImpostor, LocalDateTime.now());
		Thread.sleep(1000);
		Assert.assertTrue(procesoImpostor.seLlamoAlProcesoImpostor());
	}
	
	@Test
	public void noSeEjecutoElProcesoPlanificadoAlHorarioDado() throws InterruptedException {
		planificador.planificar(procesoImpostor, LocalDateTime.now().plus(1, ChronoUnit.HOURS));
		Thread.sleep(1000);
		Assert.assertFalse(procesoImpostor.seLlamoAlProcesoImpostor());
	}

}