package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        initSampleData();
    }

    private void initSampleData() {
    	Cliente ana = new Cliente("Ana Torres", "ana@example.com", "123456789");
        Cliente luis = new Cliente("Luis Gómez", "luis@example.com", "987654321");
        Cliente sofia = new Cliente("Sofía Méndez", "sofia@example.com", "555666777");
        save(ana);
        save(luis);
        save(sofia);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente findById(String id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.update(cliente);
    }

    public void deleteById(String id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarPorFiltros(String nombre, String email) {
        return clienteRepository.buscarPorFiltros(nombre, email);
    }
}
