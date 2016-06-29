package ar.edu.dds.tpa.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ar.edu.dds.tpa.observer.ProcesoObserver;
import ar.edu.dds.tpa.procesos.Proceso;

public class PlanificadorDeProcesos {
	private Map<LocalDateTime, Proceso> procesosPlaneados;
	List<ProcesoObserver> observadoresDeProcesos;
	ScheduledExecutorService planificador;
	
	public PlanificadorDeProcesos() {
		procesosPlaneados = new HashMap<LocalDateTime, Proceso>();
		observadoresDeProcesos = new ArrayList<ProcesoObserver>();
		planificador = Executors.newScheduledThreadPool(1);
	}
	
	public void agregarObservadorDeProcesos(ProcesoObserver observadorDeProceso) {
		observadoresDeProcesos.add(observadorDeProceso);
	}
	
	public void planificar(Proceso unProceso, LocalDateTime aUnDiaYHorario) {
		procesosPlaneados.put(aUnDiaYHorario, unProceso);
		planificador.schedule(unProceso, Duration.between(LocalDateTime.now(), aUnDiaYHorario).getSeconds(), TimeUnit.SECONDS);
		
	}
}