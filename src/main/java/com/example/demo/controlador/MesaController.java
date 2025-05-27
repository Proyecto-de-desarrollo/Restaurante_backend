package com.example.demo.controlador;

import com.example.demo.modelos.Mesa;
import com.example.demo.service.JwtService;
import com.example.demo.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
@Tag(name = "Mesas", description = "API para la gestión de mesas")
public class MesaController {

    private final MesaService mesaService;
    private final JwtService jwtService;

    @Autowired
    public MesaController(MesaService mesaService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.mesaService = mesaService;

    }

    @GetMapping
    @Operation(summary = "Obtener todas las mesas")
    public ResponseEntity<List<Mesa>> getAllMesas() {
        return new ResponseEntity<>(mesaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una mesa por su ID")
    public ResponseEntity<Mesa> getMesaById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Mesa> mesaOpt = mesaService.findById(id);
        return mesaOpt
                .map(mesa -> new ResponseEntity<>(mesa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva mesa")
    public ResponseEntity<Mesa> createMesa(@RequestHeader("Authorization") String authHeader, @RequestBody Mesa mesa) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Mesa savedMesa = mesaService.save(mesa);
        return new ResponseEntity<>(savedMesa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una mesa existente")
    public ResponseEntity<Mesa> updateMesa(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id, @RequestBody Mesa mesa) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Mesa> existenteOpt = mesaService.findById(id);
        if (existenteOpt.isPresent()) {
            mesa.setId(id);
            Mesa updatedMesa = mesaService.update(mesa);
            return new ResponseEntity<>(updatedMesa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una mesa por su ID")
    public ResponseEntity<Void> deleteMesa(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Mesa> existenteOpt = mesaService.findById(id);
        if (existenteOpt.isPresent()) {
            mesaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener todas las mesas de un usuario")
    public ResponseEntity<List<Mesa>> getMesasByUserId(@RequestHeader("Authorization") String authHeader, @PathVariable Integer userId) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Mesa> mesas = mesaService.findByUserId(userId);
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @PostMapping("/ocupar")
    @Operation(summary = "Ocupar una mesa")
    public ResponseEntity<Mesa> ocuparMesa(@RequestHeader("Authorization") String authHeader, @RequestParam int numeroMesa, @RequestParam Integer userId) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(numeroMesa);
        System.out.println(userId);
        mesaService.ocuparMesa(numeroMesa, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/desocupar")
    @Operation(summary = "Ocupar una mesa")
    public ResponseEntity<Mesa> desocuparMesa(@RequestHeader("Authorization") String authHeader, @RequestParam int numeroMesa, @RequestParam Integer userId) {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        mesaService.desocuparMesa(numeroMesa, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
