package com.example.lab2.Entity;

import com.example.lab2.Entity.Marca;
import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Tipo;

import javax.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinventario", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtipo", nullable = false)
    private Tipo idtipo;

    @Column(name = "numeroserie", length = 45)
    private String numeroserie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsede", nullable = false)
    private Sede idsede;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmarca", nullable = false)
    private Marca idmarca;

    @Column(name = "estado", length = 45)
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Marca getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Marca idmarca) {
        this.idmarca = idmarca;
    }

    public Sede getIdsede() {
        return idsede;
    }

    public void setIdsede(Sede idsede) {
        this.idsede = idsede;
    }

    public String getNumeroserie() {
        return numeroserie;
    }

    public void setNumeroserie(String numeroserie) {
        this.numeroserie = numeroserie;
    }

    public Tipo getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tipo idtipo) {
        this.idtipo = idtipo;
    }

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