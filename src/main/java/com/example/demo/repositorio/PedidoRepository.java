package com.example.demo.repositorio;

import org.springframework.stereotype.Repository;
import com.example.demo.modelos.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PedidoRepository {
    private final ConcurrentHashMap<String, Pedido> pedidoMap = new ConcurrentHashMap<>();

    public PedidoRepository() {
        // Cliente "1" (Carlos) pidió en la Mesa "1"
        save(new Pedido("1", "1", "Sancocho"));

        // Cliente "2" (Ana) pidió en la Mesa "2"
        save(new Pedido("2", "2", "Bandeja Paisa"));

        // Cliente "3" (Luis) pidió en la Mesa "3"
        save(new Pedido("3", "3", "Chuleta de cerdo"));
    }

    public Pedido save(Pedido pedido) {
        pedidoMap.put(pedido.getId(), pedido);
        return pedido;
    }

    public Pedido findById(String id) {
        return pedidoMap.get(id);
    }

    public List<Pedido> findAll() {
        return new ArrayList<>(pedidoMap.values());
    }

    public Pedido update(Pedido pedido) {
        if (pedidoMap.containsKey(pedido.getId())) {
            pedidoMap.put(pedido.getId(), pedido);
            return pedido;
        }
        return null;
    }

    public void deleteById(String id) {
        pedidoMap.remove(id);
    }

    // Buscar pedidos por ID de cliente
    public List<Pedido> findByClienteId(String clienteId) {
        List<Pedido> resultados = new ArrayList<>();
        for (Pedido pedido : pedidoMap.values()) {
            if (pedido.getClienteId().equals(clienteId)) {
                resultados.add(pedido);
            }
        }
        return resultados;
    }

    // Buscar pedidos por ID de mesa
    public List<Pedido> findByMesaId(String mesaId) {
        List<Pedido> resultados = new ArrayList<>();
        for (Pedido pedido : pedidoMap.values()) {
            if (pedido.getMesaId().equals(mesaId)) {
                resultados.add(pedido);
            }
        }
        return resultados;
    }
}
