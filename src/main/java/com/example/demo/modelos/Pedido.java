package com.example.demo.modelos;

import java.util.UUID;

public class Pedido {
    private String id;
    private String clienteId;
    private String mesaId;
    private String descripcion;

    public Pedido() {
        this.id = UUID.randomUUID().toString();
    }

    public Pedido(String clienteId, String mesaId, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.clienteId = clienteId;
        this.mesaId = mesaId;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getMesaId() { return mesaId; }
    public void setMesaId(String mesaId) { this.mesaId = mesaId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

