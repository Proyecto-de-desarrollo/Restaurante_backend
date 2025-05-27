package com.example.demo.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesa;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;


    public Pedido(Usuario usuario, Mesa mesa, String descripcion) {
        this.usuario = usuario;
        this.mesa = mesa;
        this.descripcion = descripcion;
    }

    public Pedido() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuario getCliente() { return usuario; }
    public void setCliente(Usuario usuario) { this.usuario = usuario; }

    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
