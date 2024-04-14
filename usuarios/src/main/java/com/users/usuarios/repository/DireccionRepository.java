package com.users.usuarios.repository;

import com.users.usuarios.model.Direccion;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

public interface  DireccionRepository extends JpaRepository<Direccion,Long>{
    // @Query(value = "SELECT MAX(u.id) FROM usuario u")
    // Long findMaxId();
}
