package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.dds.tpa.observer.ProcesoObserver;

public class PlanificadorDeProcesos {
	private Map<LocalDateTime, Proceso> procesosPlaneados;
	List<ProcesoObserver> observadoresDeProcesos;
	
	public PlanificadorDeProcesos() {
		procesosPlaneados = new HashMap<LocalDateTime, Proceso>();
		observadoresDeProcesos = new ArrayList<ProcesoObserver>();
	}
	
	public void agregarObservadorDeProcesos(ProcesoObserver observadorDeProceso) {
		observadoresDeProcesos.add(observadorDeProceso);
	}
	
	public void planificar(Proceso unProceso, LocalDateTime aUnDiaYHorario) {
		procesosPlaneados.put(aUnDiaYHorario, unProceso);
	}
}