package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelos.Pedido;
import com.example.demo.serivicios.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para la gestión de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return new ResponseEntity<>(pedidoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por su ID")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable String id) {
        Pedido pedido = pedidoService.findById(id);
        return pedido != null
                ? new ResponseEntity<>(pedido, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido existente")
    public ResponseEntity<Pedido> updatePedido(@PathVariable String id, @RequestBody Pedido pedido) {
        Pedido existente = pedidoService.findById(id);
        if (existente != null) {
            pedido.setId(id);
            return new ResponseEntity<>(pedidoService.update(pedido), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido por su ID")
    public ResponseEntity<Void> deletePedido(@PathVariable String id) {
        pedidoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Obtener todos los pedidos realizados por un cliente específico")
    public ResponseEntity<List<Pedido>> getPedidosByCliente(@PathVariable String clienteId) {
        return new ResponseEntity<>(pedidoService.findByClienteId(clienteId), HttpStatus.OK);
    }

    @GetMapping("/mesa/{mesaId}")
    @Operation(summary = "Obtener todos los pedidos asociados a una mesa específica")
    public ResponseEntity<List<Pedido>> getPedidosByMesa(@PathVariable String mesaId) {
        return new ResponseEntity<>(pedidoService.findByMesaId(mesaId), HttpStatus.OK);
    }
}

