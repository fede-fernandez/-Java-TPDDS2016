package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ar.edu.dds.tpa.geolocalizacion.Posicion;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_de_poi")
public abstract class PuntoDeInteres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String direccion;

	@Embedded
	private Posicion coordenadas;

	@ElementCollection
	@JoinTable(name = "PalabrasClave")
	private List<String> palabrasClave;

	private LocalDateTime fechaDeBaja;

	private boolean activo;

	public PuntoDeInteres() {

	}

	public PuntoDeInteres(String nombre, Posicion coordenadas, String direccion) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.direccion = direccion;
		activo = true;
		palabrasClave = new ArrayList<String>();
		agregarPalabraClave(nombre);
	}

	public Integer getId() {
		return id;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public String getUrl() {
		return "lugares/" + getId();
	}

	public List<String> getPalabrasClave() {
		return palabrasClave;
	}

	public void agregarPalabraClave(String unaPalabraClave) {
		palabrasClave.add(unaPalabraClave.toLowerCase());
	}

	public boolean estaCercaDe(Posicion unaPosicion) {
		return coordenadas.distanciaA(unaPosicion) <= 0.5;
	}

	public abstract boolean estaDisponibleEn(LocalDateTime unDiaYHorario);

	public boolean contienePalabraClave(String unaPalabra) {
		return palabrasClave.stream().anyMatch(unaPalabraClave -> unaPalabraClave.contains(unaPalabra.toLowerCase()));
	}

	public void borrarPalabrasClaves() {
		palabrasClave = new ArrayList<String>();
		agregarPalabraClave(nombre);

	}

	public LocalDateTime getFechaDeBaja() {
		return fechaDeBaja;
	}

	public void setFechaDeBaja(LocalDateTime fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}

	public boolean estaActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCoordenadas(Posicion posicion) {
		this.coordenadas = posicion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}