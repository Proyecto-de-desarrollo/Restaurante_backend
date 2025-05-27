package com.example.demo.service;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.modelos.Mesa;
import com.example.demo.modelos.Pedido;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositorio.MesaRepository;
import com.example.demo.repositorio.PedidoRepository;
import com.example.demo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
    }

    public Pedido save(PedidoDTO pedido) {
        Integer userId = pedido.getUserId();
        Integer mesaId = pedido.getMesaId();
        String descripcion = pedido.getDescripcion();
        Optional<Usuario> userOpt = usuarioRepository.findById(userId);
        Optional<Mesa> mesaOpt = mesaRepository.findById(mesaId);
        if (userOpt.isPresent() && mesaOpt.isPresent()) {
            Pedido nuevoPedido = new Pedido(userOpt.get(), mesaOpt.get(), descripcion);
            return pedidoRepository.save(nuevoPedido);
        }
        return null;
    }

    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido update(Pedido pedido) {
        return pedidoRepository.save(pedido); // save sirve para update también
    }

    public void deleteById(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> findByClienteId(Integer userID) {
        return pedidoRepository.findAllByUsuario_Id(userID);
    }

    public List<Pedido> findByMesaId(Integer mesaId) {
        return pedidoRepository.findAllByMesa_Id(mesaId);
    }

}
