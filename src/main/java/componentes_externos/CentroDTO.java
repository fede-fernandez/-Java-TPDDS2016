package componentes_externos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import ar.edu.dds.tpa.model.CGP;
import ar.edu.dds.tpa.model.PuntoDeInteres;
import ar.edu.dds.tpa.model.Servicio;

public class CentroDTO implements PuntosDTO {
	
	
	private int comuna;
	private String zona;
	private String director;
	private String domicilioCompleto;
	private String telefono;
	private List<ServicioDTO> serviciosDTO = new ArrayList<>();
	
	public int getComuna() {
		return comuna;
	}
	
	public String getZona() {
		return zona;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getDomicilio_completo() {
		return domicilioCompleto;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public List<Servicio> getServicios() {
		return serviciosDTO.stream().map(servicioDTO -> servicioDTO.toServicio()).collect(Collectors.toList());
	}
	
	public String getNombreComuna(){
		return String.format("Comuna %d", this.getComuna());
	}
	
	public PuntoDeInteres toPuntoDeInteres(){ 
		CGP cgp = new CGP(getNombreComuna(), new Point(0, 0), new Polygon());
		this.getServicios().stream().forEach(servicio -> cgp.agregarServicio(servicio));
		return cgp;
	};
	
	public void setComuna(int comuna) {
		this.comuna = comuna;
	}
	
	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setDomicilioCompleto(String domicilio_completo) {
		this.domicilioCompleto = domicilio_completo;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void addServiciosDTO(ServicioDTO servicioDTO) {
		this.serviciosDTO.add(servicioDTO);
	}

}
