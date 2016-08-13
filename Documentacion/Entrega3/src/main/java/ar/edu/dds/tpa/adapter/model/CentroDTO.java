package ar.edu.dds.tpa.adapter.model;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {
	private int comuna;
	private String zona;
	private String director;
	private String domicilioCompleto;
	private String telefono;
	private List<ServicioDTO> serviciosDTO = new ArrayList<>();
	
	public CentroDTO(int comuna, String zona, String director, String domicilioCompleto, String telefono,
			List<ServicioDTO> serviciosDTO) {
		this.comuna = comuna;
		this.zona = zona;
		this.director = director;
		this.domicilioCompleto = domicilioCompleto;
		this.telefono = telefono;
		this.serviciosDTO = serviciosDTO;
	}

	public int getComuna() {
		return comuna;
	}
	
	public String getZona() {
		return zona;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getDomicilioCompleto() {
		return domicilioCompleto;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public List<ServicioDTO> getServiciosDTO() {
		return serviciosDTO;
	}
}