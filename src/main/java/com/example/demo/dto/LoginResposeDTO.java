package com.example.demo.dto;

import com.example.demo.modelos.Usuario;

public class LoginResposeDTO {
    private String token;
    private Usuario usuario;

    public LoginResposeDTO(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;

        
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
