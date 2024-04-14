package com.users.usuarios.repository;

import com.users.usuarios.model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;


public interface  RolRepository extends JpaRepository<Rol,Long>{
    // @Query(value = "SELECT MAX(u.id) FROM usuario u")
    // Long findMaxId();
}
