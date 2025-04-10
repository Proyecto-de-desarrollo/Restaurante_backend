package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelos.Cliente;
import com.example.demo.serivicios.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID")
    public ResponseEntity<Cliente> getClienteById(@PathVariable String id) {
        Cliente cliente = clienteService.findById(id);
        return cliente != null
                ? new ResponseEntity<>(cliente, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        Cliente existente = clienteService.findById(id);
        if (existente != null) {
            cliente.setId(id);
            return new ResponseEntity<>(clienteService.update(cliente), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente")
    public ResponseEntity<Void> deleteCliente(@PathVariable String id) {
        Cliente existente = clienteService.findById(id);
        if (existente != null) {
            clienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar clientes por filtros")
    public ResponseEntity<List<Cliente>> buscarClientes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email) {
        return new ResponseEntity<>(clienteService.buscarPorFiltros(nombre, email), HttpStatus.OK);
    }
}