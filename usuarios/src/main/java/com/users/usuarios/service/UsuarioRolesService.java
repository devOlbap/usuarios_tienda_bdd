package com.users.usuarios.service;

import com.users.usuarios.model.UsuarioRol;

import java.util.List;
import java.util.Optional;


public interface UsuarioRolesService {
    List<UsuarioRol> getRolesUsuario(Long id);
    Optional<UsuarioRol> getRolByIdUsuario(Long id);

    UsuarioRol createRolUsuario(UsuarioRol rolUsuario);
    UsuarioRol updateRolUsuario(Long id, UsuarioRol rolUsuario);

    Boolean deleteRolUsuario(Long id);
}
