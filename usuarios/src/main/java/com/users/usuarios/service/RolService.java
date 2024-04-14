package com.users.usuarios.service;

import com.users.usuarios.model.Rol;

import java.util.List;


public interface RolService {
    List<Rol> getRoles();
    Rol getRolById(Long id);

    Rol createRol(Rol rol);
    Rol updateRol(Long id, Rol rol);

    Boolean deleteRol(Long id);
}
