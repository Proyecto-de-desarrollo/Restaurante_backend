package modelos;

import java.util.UUID;

public class Mesa {
    private String id;
    private int numero;
    private int capacidad;

    public Mesa() {
        this.id = UUID.randomUUID().toString();
    }

    public Mesa(int numero, int capacidad) {
        this.id = UUID.randomUUID().toString();
        this.numero = numero;
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setId(String id) {
        this.id = id;
    }
}
