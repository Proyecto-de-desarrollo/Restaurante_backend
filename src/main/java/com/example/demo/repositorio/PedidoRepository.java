package com.example.demo.repositorio;

import com.example.demo.modelos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findAllByUsuario_Id(Integer userId);
    List<Pedido> findAllByMesa_Id(Integer mesaId);
}
