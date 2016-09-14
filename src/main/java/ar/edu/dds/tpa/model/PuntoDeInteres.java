package ar.edu.dds.tpa.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.dds.tpa.geolocalizacion.Posicion;


@Entity
@Table(name="PuntoDeInteres")
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class PuntoDeInteres {
	
	@Id
	@GeneratedValue
	@Column(name="id_pi")
	private Long id;
	
	private String nombre;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="posicion")
	private Posicion coordenadas;
	
	@ElementCollection
	@CollectionTable(name = "Palabras_Claves", joinColumns=@JoinColumn(name="id_clave"))
	@Column(name="palabrasClave")
	private List<String> palabrasClave;
	
	
	private LocalDateTime fechaBaja;

	public PuntoDeInteres(String nombre, Posicion coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		palabrasClave = new ArrayList<String>();
		agregarPalabraClave(nombre);
	}
	
	public String getNombre() {
		return nombre;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
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
	
	public void borrarPalabrasClaves(){
		palabrasClave = new ArrayList<String>();
		agregarPalabraClave(nombre);

	}

	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Override
	public boolean equals(Object otro) {		
		//Nos referimos al mismo punto de interes si ambos objetos son exactamente el mismo, 
		//o si tienen el mismo nombre y la ubicacion es aproximadamente la misma con un leve margen de error (2mm)
		return otro != null &&
			   (otro == this ||
				   (Math.abs(this.coordenadas.distanciaA(((PuntoDeInteres)otro).getCoordenadas())) <= 0.0000002 
				   && this.getNombre().equals(((PuntoDeInteres)otro).getNombre())));
	}

	public boolean estaActivo() {
		return fechaBaja == null || fechaBaja.isAfter(LocalDateTime.now());
	}
}