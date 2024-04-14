package com.users.usuarios.service;

import com.users.usuarios.model.Direccion;

import java.util.List;
// import java.util.Optional;


public interface DireccionService {
    List<Direccion> getDirecciones();
    Direccion getDireccionById(Long id);

    Direccion createDireccion(Direccion rol);
    Direccion updateDireccion(Long id, Direccion rol);

    Boolean deleteDireccion(Long id);
}
