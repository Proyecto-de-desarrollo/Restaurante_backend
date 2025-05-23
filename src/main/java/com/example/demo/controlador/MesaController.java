package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelos.Mesa;
import com.example.demo.serivicios.MesaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@Tag(name = "Mesas", description = "API para la gestión de mesas")
public class MesaController {
    private final MesaService mesaService;

    @Autowired
    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las mesas")
    public ResponseEntity<List<Mesa>> getAllMesas() {
        return new ResponseEntity<>(mesaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una mesa por su ID")
    public ResponseEntity<Mesa> getMesaById(@PathVariable String id) {
        Mesa mesa = mesaService.findById(id);
        return mesa != null
                ? new ResponseEntity<>(mesa, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva mesa")
    public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
        return new ResponseEntity<>(mesaService.save(mesa), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una mesa existente")
    public ResponseEntity<Mesa> updateMesa(@PathVariable String id, @RequestBody Mesa mesa) {
        Mesa existente = mesaService.findById(id);
        if (existente != null) {
            mesa.setId(id);
            return new ResponseEntity<>(mesaService.update(mesa), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una mesa por su ID")
    public ResponseEntity<Void> deleteMesa(@PathVariable String id) {
        Mesa existente = mesaService.findById(id);
        if (existente != null) {
            mesaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

