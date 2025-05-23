package com.example.demo.repositorio;

import org.springframework.stereotype.Repository;
import com.example.demo.modelos.Mesa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MesaRepository {
    private final ConcurrentHashMap<String, Mesa> mesaMap = new ConcurrentHashMap<>();

    public MesaRepository() {
        save(new Mesa("1", 1, 4));
        save(new Mesa("2", 2, 2));
        save(new Mesa("3", 3, 6));
    }

    public Mesa save(Mesa mesa) {
        mesaMap.put(mesa.getId(), mesa);
        return mesa;
    }

    public Mesa findById(String id) {
        return mesaMap.get(id);
    }

    public List<Mesa> findAll() {
        return new ArrayList<>(mesaMap.values());
    }

    public Mesa update(Mesa mesa) {
        if (mesaMap.containsKey(mesa.getId())) {
            mesaMap.put(mesa.getId(), mesa);
            return mesa;
        }
        return null;
    }

    public void deleteById(String id) {
        mesaMap.remove(id);
    }
}
