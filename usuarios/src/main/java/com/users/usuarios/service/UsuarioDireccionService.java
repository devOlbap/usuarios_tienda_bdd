package com.users.usuarios.service;

import com.users.usuarios.model.UsuarioDireccion;

import java.util.List;
import java.util.Optional;


public interface UsuarioDireccionService {
    List<UsuarioDireccion> getUsuarioDirecciones();
    Optional<UsuarioDireccion> getUsuarioDireccionById(Long id);

    UsuarioDireccion createUsuarioDireccion(UsuarioDireccion direccionUsuario);
    UsuarioDireccion updateUsuarioDireccion(Long id, UsuarioDireccion direccionUsuario);

    Boolean deleteUsuarioDireccion(Long id);

}
