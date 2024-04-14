package com.users.usuarios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
@Table(name="usuario_direccion")
public class UsuarioDireccion {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        name= "id_usuario_direccion"
    )
    private Long id;
    
    @Column(
        nullable = false,
        name= "usuario_id_usuario"
    )
    private Long usuario_id_usuario;
    @Column(
        nullable = false,
        name= "direccion_id_direccion"
    )
    private Long direccion_id_direccion;
    
    public UsuarioDireccion(){}

    public UsuarioDireccion(Long id, Long usuario_id_usuario, Long direccion_id_direccion) {
        this.id = id;
        this.usuario_id_usuario = usuario_id_usuario;
        this.direccion_id_direccion = direccion_id_direccion;

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
    public Long getDireccionIdDireccion() {
        return direccion_id_direccion;
    }

    public void setDireccionIdDireccion(Long id) {
        this.direccion_id_direccion = id;
    }
    
}