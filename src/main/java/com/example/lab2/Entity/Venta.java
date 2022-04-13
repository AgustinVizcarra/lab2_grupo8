package com.example.lab2.Entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @Column(name = "idventa", nullable = false, length = 45)
    private Integer id;

    @MapsId("dniTrabajador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dniTrabajador", nullable = false)
    private Trabajador dniTrabajador;

    @Column(name = "fecha")
    private Instant fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario idInventario;

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public Trabajador getDniTrabajador() {
        return dniTrabajador;
    }

    public void setDniTrabajador(Trabajador dniTrabajador) {
        this.dniTrabajador = dniTrabajador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}