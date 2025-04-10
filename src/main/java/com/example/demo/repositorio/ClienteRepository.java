package com.example.demo.repositorio;

import org.springframework.stereotype.Repository;
import com.example.demo.modelos.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ClienteRepository {
    private final ConcurrentHashMap<String, Cliente> clienteMap = new ConcurrentHashMap<>();

    public ClienteRepository() {
        save(new Cliente("1", "Carlos López", "carlos@example.com", "123456789"));
        save(new Cliente("2", "Ana Torres", "ana@example.com", "987654321"));
        save(new Cliente("3", "Luis Mejía", "luis@example.com", "555123456"));
    }

    public Cliente save(Cliente cliente) {
        clienteMap.put(cliente.getId(), cliente);
        return cliente;
    }

    public Cliente findById(String id) {
        return clienteMap.get(id);
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(clienteMap.values());
    }

    public Cliente update(Cliente cliente) {
        if (clienteMap.containsKey(cliente.getId())) {
            clienteMap.put(cliente.getId(), cliente);
            return cliente;
        }
        return null;
    }

    public void deleteById(String id) {
        clienteMap.remove(id);
    }

    public List<Cliente> buscarPorFiltros(String nombre, String email) {
        List<Cliente> resultados = new ArrayList<>();
        for (Cliente cliente : clienteMap.values()) {
            boolean coincideNombre = (nombre == null || cliente.getNombre().toLowerCase().contains(nombre.toLowerCase()));
            boolean coincideEmail = (email == null || cliente.getEmail().toLowerCase().contains(email.toLowerCase()));
            if (coincideNombre && coincideEmail) {
                resultados.add(cliente);
            }
        }
        return resultados;
    }

    public List<Cliente> saveAll(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            save(cliente);
        }
        return clientes;
    }
}