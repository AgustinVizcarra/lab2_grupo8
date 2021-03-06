package com.example.lab2.Entity;

import javax.persistence.*;

@Entity
@Table(name = "trabajadores")
public class Trabajador {
    @Id
    @Column(name = "dni", nullable = false, length = 45)
    private String id;

    @Column(name = "nombres", length = 45)
    private String nombres;

    @Column(name = "apellidos", length = 45)
    private String apellidos;

    @Column(name = "correo", length = 45)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsede", nullable = false)
    private Sede idsede;

    public Sede getIdsede() {
        return idsede;
    }

    public void setIdsede(Sede idsede) {
        this.idsede = idsede;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}