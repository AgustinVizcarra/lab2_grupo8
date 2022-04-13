package com.example.lab2.Entity;

import javax.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido", nullable = false)
    private Integer id;

    @Column(name = "codigoOrden", length = 45)
    private String codigoOrden;

    @Column(name = "nombreCliente", length = 45)
    private String nombreCliente;

    @Column(name = "dniCliente", length = 45)
    private String dniCliente;

    @Column(name = "apellidoCliente", length = 45)
    private String apellidoCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario idInventario;

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}