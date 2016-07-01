package ar.edu.dds.tpa.procesos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlanificadorDeProcesos {
	private Map<LocalDateTime, Proceso> procesosPlaneados;
	ScheduledExecutorService planificador;
	
	public PlanificadorDeProcesos() {
		procesosPlaneados = new HashMap<LocalDateTime, Proceso>();
		planificador = Executors.newScheduledThreadPool(1);
	}
	
	public void planificar(Proceso unProceso, LocalDateTime aUnDiaYHorario) {
		procesosPlaneados.put(aUnDiaYHorario, unProceso);
		planificador.schedule(unProceso, Duration.between(LocalDateTime.now(), aUnDiaYHorario).getSeconds(), TimeUnit.SECONDS);
	}
}