package componentes_externos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.dds.tpa.model.HorarioDeAtencion;
import ar.edu.dds.tpa.model.RangoDeHorario;
import ar.edu.dds.tpa.model.Servicio;

public class ServicioDTO {
	private String nombre;
	private List<RangosServicioDTO> rangosServicioDTO = new ArrayList<>();
	
	public Servicio toServicio() {
		//return new Servicio(nombre, getHorarioDeAtencion());
		return new Servicio("Un buen servicio :)", getHorarioDeAtencion());
	}

	private HorarioDeAtencion getHorarioDeAtencion() {
		HorarioDeAtencion horarioDeAtencion = new HorarioDeAtencion();
		rangosServicioDTO.stream().forEach(horarioDTO -> horarioDeAtencion.agregarRangoDeHorario(horarioDTO.getDiaDeLaSemana(), horarioDTO.getRangoHorario()));
		return horarioDeAtencion;
	}
	
	public void addRangoServicioDTO(RangosServicioDTO rangoServicioDTO){
		this.rangosServicioDTO.add(rangoServicioDTO);
	}

}
