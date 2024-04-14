package com.users.usuarios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.users.usuarios.service.UsuarioDireccionService;
import com.users.usuarios.service.DireccionService;

import com.users.usuarios.model.UsuarioDireccion;
import com.users.usuarios.model.Direccion;

// import java.util.HashMap;
// import java.util.Map;

import java.util.List;
// import java.lang.reflect.Array;
import java.util.ArrayList;
// import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioDireccionController {

    @Autowired
    private UsuarioDireccionService userDireccionService;

    @Autowired
    private DireccionService direccionService;

    @GetMapping("/{id_usuario}/direcciones")
    public ResponseEntity<List<Direccion>> getDireccionesByUser(@PathVariable Long id_usuario){
        List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
        // Crear una lista para almacenar las direcciones
        List<Direccion> direcciones = new ArrayList<>();
        for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {
            // Obtener la dirección correspondiente al UsuarioDireccion actual
            if(id_usuario == usuarioDireccion.getUsuarioIdUsuario()){
                Direccion direccion = direccionService.getDireccionById(usuarioDireccion.getDireccionIdDireccion());
                // Agregar la dirección a la lista de direcciones
                direcciones.add(direccion);
            }
            
        }
        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/{id_usuario}/direcciones/{id_direccion}")
    public Direccion getDireccionUserById(@PathVariable Long id_usuario,@PathVariable Long id_direccion ) {
        List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
        Direccion direccion = new Direccion();

        for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {
            if(id_usuario == usuarioDireccion.getUsuarioIdUsuario() && id_direccion == usuarioDireccion.getDireccionIdDireccion()){
                direccion = direccionService.getDireccionById(id_direccion);
            }
        }

        return direccion;

    }

    //GET LISTOS

    @PostMapping("/{id_usuario}/direcciones/add")
    public ResponseEntity<?> postDireccionUsuario(@PathVariable Long id_usuario, @RequestBody Long id_direccion) {
        List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
        long maxId = Long.MIN_VALUE;

        for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {
            long id = usuarioDireccion.getId();

            if (id > maxId) {
                maxId = id;
            }
            if (id_usuario == usuarioDireccion.getUsuarioIdUsuario() &&
                    id_direccion== usuarioDireccion.getDireccionIdDireccion()) {
                // Si entra aquí es porque ya existe el registro y debemos retornarlo
                return ResponseEntity.status(409).body(usuarioDireccion);
            }
        }

        Direccion direccion_nueva = new Direccion();

        direccion_nueva = direccionService.getDireccionById(id_direccion);

        if (direccion_nueva == null) {
            //Si la dirección no existe, agregar un mensaje de error a la lista
            List<String> errores = new ArrayList<>();
            errores.add("Error al obtener la dirección");
            return ResponseEntity.badRequest().body(errores);
        }

        UsuarioDireccion userDireccion = new UsuarioDireccion();

        userDireccion.setId(maxId+1);
        userDireccion.setUsuarioIdUsuario(id_usuario);
        userDireccion.setDireccionIdDireccion(direccion_nueva.getId());

        userDireccionService.createUsuarioDireccion(userDireccion);

        return ResponseEntity.ok(direccion_nueva);
    }


}
