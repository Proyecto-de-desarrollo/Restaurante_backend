package com.example.demo.modelos;

import java.util.UUID;

public class Cliente {
    private String id;
    private String nombre;
    private String email;
    private String telefono;

    // Constructor con ID fijo
    public Cliente(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Constructor con ID aleatorio
    public Cliente(String nombre, String email, String telefono) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Cliente() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
