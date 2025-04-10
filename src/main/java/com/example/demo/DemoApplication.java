package com.example.demo;

import com.example.demo.modelos.Cliente;
import com.example.demo.modelos.Mesa;
import com.example.demo.modelos.Pedido;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.MesaRepository;
import com.example.demo.repositorio.PedidoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

   
    @Bean
    public CommandLineRunner cargarDatosIniciales(PedidoRepository pedidoRepository,
                                                  ClienteRepository clienteRepository,
                                                  MesaRepository mesaRepository) {
        return args -> {
            Cliente cliente1 = new Cliente("Juan Pérez", "juan@example.com", "123456789");
            Cliente cliente2 = new Cliente("Ana López", "ana@example.com", "987654321");
            clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));

            Mesa mesa1 = new Mesa(1, 4);
            Mesa mesa2 = new Mesa(2, 2);
            mesaRepository.saveAll(Arrays.asList(mesa1, mesa2));

            Pedido pedido1 = new Pedido(cliente1.getId(), mesa1.getId(), "Bandeja Paisa");
            Pedido pedido2 = new Pedido(cliente2.getId(), mesa2.getId(), "Sancocho");
            pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));

            System.out.println("Pedidos creados automáticamente.");
        };
    }
}
