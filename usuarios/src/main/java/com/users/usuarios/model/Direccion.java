package com.users.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
@Table(name="direccion")
public class Direccion {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        name= "id_direccion"
    )
    private Long id;

    private String calle;
    private String numeracion;
    private String otro;
    private char estado;

    // Constructor vacío requerido por JPA
    public Direccion() {}

    public Direccion(String calle, String numeracion, String otro, char estado) {
        this.calle = calle;
        this.numeracion = numeracion;
        this.otro = otro;
        this.estado = estado;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }


    public List<String> validarCampos() {
        List<String> mensajes = new ArrayList<>();
    
        if (!validarCalle()) {
            mensajes.add("La calle debe tener entre 5 y 255 caracteres.");
        }
        if (!validarNumeracion()) {
            mensajes.add("La numeración debe tener entre 2 y 255 caracteres.");
        }
    
        return mensajes;
    }

    private boolean validarCalle() {
        return calle != null && calle.length() >= 5 && calle.length() <= 255;
    }
    
    private boolean validarNumeracion() {
        return numeracion != null && numeracion.length() >= 2 && numeracion.length() <= 255;
    }
 

}
