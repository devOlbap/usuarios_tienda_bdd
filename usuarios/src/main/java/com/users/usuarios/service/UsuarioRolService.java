package com.users.usuarios.service;

import com.users.usuarios.model.UsuarioRol;

import java.util.List;
import java.util.Optional;


public interface UsuarioRolService {
    List<UsuarioRol> getUsuarioRoles();
    Optional<UsuarioRol> getUsuarioRolById(Long id);

    UsuarioRol createUsuarioRol(UsuarioRol RolUsuario);
    UsuarioRol updateUsuarioRol(Long id, UsuarioRol RolUsuario);

    Boolean deleteUsuarioRol(Long id);

}
