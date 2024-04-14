package com.users.usuarios.service;

import com.users.usuarios.model.Usuario;

import java.util.List;


public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario getUsuarioById(Long id);
    Usuario getUsuarioByUsername(String username);

    Usuario createUsuario(Usuario usuario);
    Usuario updateUsuario(Long id, Usuario usuario);

    Boolean deleteUsuario(Long id);
}

