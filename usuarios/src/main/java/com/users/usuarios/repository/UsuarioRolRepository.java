package com.users.usuarios.repository;

import com.users.usuarios.model.UsuarioRol;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;


public interface  UsuarioRolRepository extends JpaRepository<UsuarioRol,Long>{
    // @Query(value = "SELECT MAX(u.id) FROM usuario u")
    // Long findMaxId();
}