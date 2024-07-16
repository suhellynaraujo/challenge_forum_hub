package com.aluraone.forumHub.service;

import com.aluraone.forumHub.domain.usuario.Usuario;
import com.aluraone.forumHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByEmail(String email) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return usuario;
    }
}
