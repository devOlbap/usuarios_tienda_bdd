package com.users.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="rol")
public class Rol {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        name= "id_rol"
    )
    private Long id;
    
    @Column(
        nullable = false,
        name= "descripcion"
    )
    private String descripcion;
    @Column(
        nullable = false,
        name= "estado"
    )
    private char estado;
    

    public Rol() {}

    public Rol(String descripcion, char estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public List<String> validarCampos() {
        List<String> mensajes = new ArrayList<>();
    
        if (!validarDescripcion()) {
            mensajes.add("La descripcion debe tener entre 5 y 255 caracteres.");
        }

        return mensajes;
    }

    private boolean validarDescripcion() {
        return descripcion != null && descripcion.length() >= 5 && descripcion.length() <= 255;
    }
    
}
