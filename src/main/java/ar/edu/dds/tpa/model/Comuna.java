package ar.edu.dds.tpa.model;

import javax.persistence.*;

@Entity
public class Comuna {
    @Id
    private Integer numero;

    private String nombre;

    public Comuna(Integer numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }
}
