package com.example.demo.repositorio;

import com.example.demo.modelos.Mesa;
import com.example.demo.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    List<Mesa> findAllByUser_Id(Integer userId);
}
