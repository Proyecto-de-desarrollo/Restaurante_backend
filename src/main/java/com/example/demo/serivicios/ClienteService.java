package com.example.demo.serivicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelos.Cliente;
import com.example.demo.modelos.Pedido;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.PedidoRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
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

    public List<Pedido> getPedidosPorCliente(String clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}
