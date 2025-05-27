package com.example.demo.service;

import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.InvalidUsernameException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario save(Usuario usuario) throws InvalidUsernameException {
        Optional<Usuario> aux = usuarioRepository.findByEmail(usuario.getEmail());
        if (aux.isPresent()) {
            throw new InvalidUsernameException("El email ya está registrado");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Integer id) throws NotFoundException {
        Optional<Usuario> aux = usuarioRepository.findById(id);
        if (aux.isEmpty()) {
            throw new NotFoundException("No se encontró el usuario");
        }
        return aux.get();
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String password) throws InvalidPasswordException {
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new InvalidPasswordException("Credenciales invalidas");
        }
        if (!passwordEncoder.matches(password, user.get().getContrasena())) {
            throw new InvalidPasswordException("Credenciales invalidas");
        }
        return user.get();
    }
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
