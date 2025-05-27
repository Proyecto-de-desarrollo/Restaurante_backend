package com.example.demo.service;

import com.example.demo.modelos.Mesa;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositorio.MesaRepository;
import com.example.demo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MesaService(MesaRepository mesaRepository, UsuarioRepository usuarioRepository) {
        this.mesaRepository = mesaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> findById(Integer id) {
        return mesaRepository.findById(id);
    }

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Mesa update(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void deleteById(Integer id) {
        mesaRepository.deleteById(id);
    }

    public List<Mesa> findByUserId(Integer userId) {
        List<Mesa> mesas = mesaRepository.findAllByUser_Id(userId);
        Usuario user = usuarioRepository.findById(userId).get();
        if (mesas.size() != 16) {
            for (int i = mesas.size(); i < 16; i++) {
                Mesa mesa = new Mesa();
                mesa.setCapacidad(4);
                mesa.setNumero(i + 1);
                mesa.setOcupada(false);
                mesa.setUser(user);
                mesaRepository.save(mesa);
            }
            return mesaRepository.findAllByUser_Id(userId);
        }
        return mesas;
    }

    public void ocuparMesa(int numero, Integer userId) {
        List<Mesa> mesas = mesaRepository.findAllByUser_Id(userId);
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numero) {
                mesa.setOcupada(true);
                mesaRepository.save(mesa);
            }
        }
    }

    public void desocuparMesa(int numero, Integer userId) {
        List<Mesa> mesas = mesaRepository.findAllByUser_Id(userId);
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numero) {
                mesa.setOcupada(false);
                mesaRepository.save(mesa);
            }
        }
    }
}
