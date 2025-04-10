package com.example.demo.repositorio;

import org.springframework.stereotype.Repository;

import com.example.demo.modelos.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PedidoRepository {
    private final ConcurrentHashMap<String, Pedido> pedidos = new ConcurrentHashMap<>();

    public Pedido save(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    public Pedido findById(String id) {
        return pedidos.get(id);
    }

    public List<Pedido> findAll() {
        return new ArrayList<>(pedidos.values());
    }

    public Pedido update(Pedido pedido) {
        if (pedidos.containsKey(pedido.getId())) {
            pedidos.put(pedido.getId(), pedido);
            return pedido;
        }
        return null;
    }

    public void deleteById(String id) {
        pedidos.remove(id);
    }

    public List<Pedido> findByClienteId(String clienteId) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos.values()) {
            if (p.getClienteId().equals(clienteId)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Pedido> findByMesaId(String mesaId) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos.values()) {
            if (p.getMesaId().equals(mesaId)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}

