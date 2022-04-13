package com.example.lab2.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tipos")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}