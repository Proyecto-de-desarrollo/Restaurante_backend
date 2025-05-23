package com.example.demo.serivicios;

import org.springframework.stereotype.Service;

import com.example.demo.modelos.Mesa;
import com.example.demo.repositorio.MesaRepository;

import java.util.List;

@Service
public class MesaService {
    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa findById(String id) {
        return mesaRepository.findById(id);
    }

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Mesa update(Mesa mesa) {
        return mesaRepository.update(mesa);
    }

    public void deleteById(String id) {
        mesaRepository.deleteById(id);
    }
}
