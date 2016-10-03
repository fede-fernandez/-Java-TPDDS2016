package ar.edu.dds.tpa.observer;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ar.edu.dds.tpa.model.Busqueda;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_DE_OBSERVADOR_DE_BUSQUEDA")
public abstract class BusquedaObserver {

	@Id
	@GeneratedValue
	private Integer id;

	public BusquedaObserver() {

	}

	public abstract void informar(Busqueda unaBusqueda);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}