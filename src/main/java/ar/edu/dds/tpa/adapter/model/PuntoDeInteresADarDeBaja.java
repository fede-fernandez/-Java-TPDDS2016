package ar.edu.dds.tpa.adapter.model;

import java.time.LocalDateTime;

public class PuntoDeInteresADarDeBaja {
	
	private Integer id;
	
	private LocalDateTime deletedAt;

	public Integer getId() {
		return id;
	}

	public LocalDateTime getFechaDeBaja() {
		return deletedAt;
	}
}