package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ar.edu.dds.tpa.geolocalizacion.Posicion;


@Entity 
public class LocalComercial extends PuntoDeInteres {
	
	@OneToOne
	private Rubro rubro;
	
	@OneToOne
	private HorarioDeAtencion horarioDeAtencion;

	public LocalComercial(String nombre, Posicion coordenadas, Rubro rubro) {
		super(nombre, coordenadas);
		this.rubro = rubro;
		horarioDeAtencion = new HorarioDeAtencion();
		if(rubro != null) {
			super.agregarPalabraClave(rubro.getNombre());
		}
	}

	public Rubro getRubro() {
		return rubro;
	}
		
	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta);
	}
	
	public void agregarHorarioDeAtencionComunEnVariosDias(List<DayOfWeek> dias, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(dias, horarioDesde, horarioHasta);
	}

	@Override
	public boolean estaCercaDe(Posicion unaPosicion) {
		return getCoordenadas().distanciaA(unaPosicion) <= rubro.getRadioDeCercania() * 0.1;
	}

	@Override
	public boolean estaDisponibleEn(LocalDateTime unDiaYHorario) {
		return horarioDeAtencion.seAtiendeEn(unDiaYHorario);
	}
}