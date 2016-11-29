package ar.edu.dds.tpa.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

@Entity
public class LocalComercial extends PuntoDeInteres {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rubro_id")
	private Rubro rubro;

	@OneToOne(cascade = CascadeType.ALL)
	private HorarioDeAtencion horarioDeAtencion;

	public LocalComercial() {

	}

	public LocalComercial(String nombre, Posicion coordenadas, Rubro rubro, String direccion) {
		super(nombre, coordenadas, direccion);
		this.rubro = rubro;
		horarioDeAtencion = new HorarioDeAtencion();
		if (rubro != null) {
			super.agregarPalabraClave(rubro.getNombre());
		}
	}

	public Rubro getRubro() {
		return rubro;
	}

	public HorarioDeAtencion getHorarioDeAtencion() {
		return horarioDeAtencion;
	}

	public void agregarHorarioDeAtencion(DayOfWeek unDia, LocalTime horarioDesde, LocalTime horarioHasta) {
		horarioDeAtencion.agregarHorarioDeAtencion(unDia, horarioDesde, horarioHasta);
	}

	public void agregarHorarioDeAtencionComunEnVariosDias(List<DayOfWeek> dias, LocalTime horarioDesde,
			LocalTime horarioHasta) {
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