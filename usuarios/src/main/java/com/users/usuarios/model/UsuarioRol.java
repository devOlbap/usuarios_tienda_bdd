package com.users.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
@Table(name="usuario_rol")
public class UsuarioRol {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        name= "id_usuario_rol"
    )
    private Long id;
    
    @Column(
        nullable = false,
        name= "usuario_id_usuario"
    )
    private Long usuario_id_usuario;
    @Column(
        nullable = false,
        name= "rol_id_rol"
    )
    private Long rol_id_rol;

    public UsuarioRol(){

    };
    
    public UsuarioRol(Long id, Long usuario_id_usuario, Long rol_id_rol) {
        this.id = id;
        this.usuario_id_usuario = usuario_id_usuario;
        this.rol_id_rol = rol_id_rol;

    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioIdUsuario() {
        return usuario_id_usuario;
    }

    public void setUsuarioIdUsuario(Long id) {
        this.usuario_id_usuario = id;
    }
    public Long getRolIdRol() {
        return rol_id_rol;
    }

    public void setRolIdRol(Long id) {
        this.rol_id_rol = id;
    }
    
}