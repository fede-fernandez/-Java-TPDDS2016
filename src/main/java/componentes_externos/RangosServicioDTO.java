package componentes_externos;

import java.time.DayOfWeek;
import java.time.LocalTime;

import ar.edu.dds.tpa.model.HorarioDeAtencion;
import ar.edu.dds.tpa.model.RangoDeHorario;

public class RangosServicioDTO {
	private int dia;
	private int horaDesde;
	private int minutoDesde;
	private int horaHasta;
	private int minutoHasta;
	
	public RangosServicioDTO(int dia, int horaDesde, int minutoDesde, int horaHasta, int minutoHasta) {
		this.dia = dia;
		this.horaDesde = horaDesde;
		this.minutoDesde = minutoDesde;
		this.horaHasta = horaHasta;
		this.minutoHasta = minutoHasta;
	}
	
	public int getDia() {
		//return dia;
		return 1;
	}
	public int getHoraDesde() {
		//return horaDesde;
		return 1;
	}
	public int getMinutoDesde() {
		//return minutoDesde;
		return 1;
	}
	public int getHoraHasta() {
		//return horaHasta;
		return 1;
	}
	public int getMinutoHasta() {
		//return minutoHasta;
		return 1;
	}
	public DayOfWeek getDiaDeLaSemana() {
		return DayOfWeek.of(this.getDia());
	}
	
	public RangoDeHorario getRangoHorario(){
		return new RangoDeHorario(LocalTime.of(this.getHoraDesde(), this.getMinutoDesde()), LocalTime.of(this.getHoraHasta(), this.getMinutoHasta()));
	}
}
