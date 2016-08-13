package ar.edu.dds.tpa.adapter.model;

public class RangoServicioDTO {
	private int numeroDia;
	private int horarioDesde;
	private int minutosDesde;
	private int horarioHasta;
	private int minutosHasta;
	
	public RangoServicioDTO(int numeroDia, int horarioDesde, int minutosDesde, int horarioHasta, int minutosHasta) {
		this.numeroDia = numeroDia;
		this.horarioDesde = horarioDesde;
		this.minutosDesde = minutosDesde;
		this.horarioHasta = horarioHasta;
		this.minutosHasta = minutosHasta;
	}

	public int getNumeroDia() {
		return numeroDia;
	}
	
	public int getHorarioDesde() {
		return horarioDesde;
	}
	
	public int getMinutosDesde() {
		return minutosDesde;
	}
	
	public int getHorarioHasta() {
		return horarioHasta;
	}
	
	public int getMinutosHasta() {
		return minutosHasta;
	}
}