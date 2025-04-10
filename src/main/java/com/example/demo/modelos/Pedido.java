package com.example.demo.modelos;

import java.util.UUID;

public class Pedido {
    private String id;
    private String clienteId;
    private String mesaId;
    private String descripcion;

    // Constructor con ID fijo
    public Pedido(String id, String clienteId, String mesaId, String descripcion) {
        this.id = id;
        this.clienteId = clienteId;
        this.mesaId = mesaId;
        this.descripcion = descripcion;
    }

    // Constructor con ID aleatorio
    public Pedido(String clienteId, String mesaId, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.clienteId = clienteId;
        this.mesaId = mesaId;
        this.descripcion = descripcion;
    }

    public Pedido() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getMesaId() { return mesaId; }
    public void setMesaId(String mesaId) { this.mesaId = mesaId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
