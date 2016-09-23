package ar.edu.dds.tpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class IntervalosDeHorario {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private HorarioDeAtencion horarioDeAtencion;

	@OneToMany
	@JoinTable(name = "RangosDeHorarioEnUnDia")
	private List<RangoDeHorario> rangosDeHorario;

	public IntervalosDeHorario() {
		rangosDeHorario = new ArrayList<RangoDeHorario>();
	}

	public void agregar(RangoDeHorario unRangoDeHorario) {
		rangosDeHorario.add(unRangoDeHorario);
	}

	public List<RangoDeHorario> obtenerRangosDeHorario() {
		return rangosDeHorario;
	}
}