package com.example.demo.controlador;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResposeDTO;
import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.InvalidUsernameException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.modelos.Usuario;
import com.example.demo.service.JwtService;
import com.example.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;


    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public ResponseEntity<List<Usuario>> getAllClientes() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID")
    public ResponseEntity<Usuario> getClienteById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws NotFoundException {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Usuario usuario = usuarioService.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<Usuario> createCliente(@RequestBody Usuario usuario) throws InvalidUsernameException {
        Usuario savedUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente")
    public ResponseEntity<Usuario> updateCliente(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id, @RequestBody Usuario usuario) throws NotFoundException {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Usuario existenteOpt = usuarioService.findById(id);
        usuario.setId(existenteOpt.getId());
        Usuario updated = usuarioService.update(usuario);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente")
    public ResponseEntity<Void> deleteCliente(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws NotFoundException {
        String token = jwtService.extractToken(authHeader);
        if (!jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Usuario existenteOpt = usuarioService.findById(id);
        usuarioService.deleteById(existenteOpt.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity<LoginResposeDTO> login(@RequestBody LoginRequestDTO requestDTO) throws InvalidPasswordException {
        Usuario usuario = usuarioService.login(requestDTO.getEmail(), requestDTO.getPassword());
        String token = jwtService.generateJwtToken(usuario);
        return new ResponseEntity<>(new LoginResposeDTO(token, usuario), HttpStatus.OK);
    }
}
