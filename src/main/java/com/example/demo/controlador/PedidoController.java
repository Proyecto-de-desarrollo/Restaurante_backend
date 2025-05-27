package com.example.demo.controlador;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.modelos.Pedido;
import com.example.demo.service.JwtService;
import com.example.demo.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para la gestión de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final JwtService jwtService;
    @Autowired
    public PedidoController(PedidoService pedidoService, JwtService jwtService) {
        this.pedidoService = pedidoService;
        this.jwtService = jwtService;

        
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return new ResponseEntity<>(pedidoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por su ID")
    public ResponseEntity<Pedido> getPedidoById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Pedido> pedidoOpt = pedidoService.findById(id);
        return pedidoOpt
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido")
    public ResponseEntity<Pedido> createPedido(@RequestHeader("Authorization") String authHeader, @RequestBody PedidoDTO pedido) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Pedido savedPedido = pedidoService.save(pedido);
        return new ResponseEntity<>(savedPedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido existente")
    public ResponseEntity<Pedido> updatePedido(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id, @RequestBody Pedido pedido) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Pedido> existenteOpt = pedidoService.findById(id);
        if (existenteOpt.isPresent()) {
            pedido.setId(id);
            Pedido updatedPedido = pedidoService.update(pedido);
            return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido por su ID")
    public ResponseEntity<Void> deletePedido(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        pedidoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener todos los pedidos realizados por un cliente específico")
    public ResponseEntity<List<Pedido>> getPedidosByUserId(@RequestHeader("Authorization") String authHeader, @PathVariable Integer userId) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Pedido> pedidos = pedidoService.findByClienteId(userId);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/mesa/{mesaId}")
    @Operation(summary = "Obtener todos los pedidos asociados a una mesa específica")
    public ResponseEntity<List<Pedido>> getPedidosByMesa(@RequestHeader("Authorization") String authHeader, @PathVariable Integer mesaId) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Pedido> pedidos = pedidoService.findByMesaId(mesaId);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}
