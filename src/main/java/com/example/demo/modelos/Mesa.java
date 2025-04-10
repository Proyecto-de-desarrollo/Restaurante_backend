package com.example.demo.modelos;

import java.util.UUID;

public class Mesa {
    private String id;
    private int numero;
    private int capacidad;

    // Constructor con ID fijo
    public Mesa(String id, int numero, int capacidad) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
    }

    // Constructor con ID aleatorio
    public Mesa(int numero, int capacidad) {
        this.id = UUID.randomUUID().toString();
        this.numero = numero;
        this.capacidad = capacidad;
    }

    public Mesa() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}
