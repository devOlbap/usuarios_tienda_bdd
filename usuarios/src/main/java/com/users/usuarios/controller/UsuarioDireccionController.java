package com.users.usuarios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.users.usuarios.service.UsuarioDireccionService;
import com.users.usuarios.service.UsuarioService;
import com.users.usuarios.service.DireccionService;

import com.users.usuarios.model.UsuarioDireccion;
import com.users.usuarios.model.Direccion;
import com.users.usuarios.model.Usuario;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/usuarios")
public class UsuarioDireccionController {

    @Autowired
    private UsuarioDireccionService userDireccionService;

    @Autowired
    private DireccionService direccionService;


    @Autowired
    private UsuarioService userService;

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

    /*
        traté de crear un DireccionRequest para recibir un JSON custom pero cuando queria recibir el id
        de la direccion ... lo tomaba como 0 
        Pero si le quito el cuerpo y envio el numero solo... lo toma bien lo 
        tengo que convertirlo en Long para validar el dato
    */
    @PostMapping("/{id_usuario}/direcciones/add")
    public ResponseEntity<?> postDireccionUsuario(@PathVariable Long id_usuario, @RequestBody int dir) {
        
        List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
        long maxId = Long.MIN_VALUE;
        List<String> errores = new ArrayList<>();

        // int id_direccion = dir.getIdDireccion(); //DireccionRequest.method()

        Long aux = Long.valueOf(dir);

        Direccion direccion_nueva = direccionService.getDireccionById(aux);

        Usuario user = userService.getUsuarioById(id_usuario);

        if(user == null){
            errores.add("Error al buscar usuario.");
            return ResponseEntity.badRequest().body(errores);
        }
        if (direccion_nueva == null) {
            //Si la dirección no existe, agregar un mensaje de error a la lista
            errores.add("Error al obtener la dirección");
            return ResponseEntity.badRequest().body(errores);
        }

        for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {
            long id = usuarioDireccion.getId();

            if (id > maxId) {
                maxId = id;
            }
            if (usuarioDireccion.getUsuarioIdUsuario().equals(id_usuario) &&
                    usuarioDireccion.getDireccionIdDireccion().equals(direccion_nueva.getId())) {
                // Si entra aquí es porque ya existe el registro y debemos retornarlo
                errores.add("Ya existe registro del usuario: "+id_usuario+" con la direccion: "+direccion_nueva.getCalle());
                return ResponseEntity.status(409).body(errores);
            }
        }

        UsuarioDireccion userDireccion = new UsuarioDireccion();

        userDireccion.setId(maxId+1);
        userDireccion.setUsuarioIdUsuario(id_usuario);
        userDireccion.setDireccionIdDireccion(direccion_nueva.getId());

        userDireccionService.createUsuarioDireccion(userDireccion);

        return ResponseEntity.ok(direccion_nueva);
    }

    @DeleteMapping("/{id_usuario}/direcciones/{id_direccion}")
    public ResponseEntity<?> deleteDireccionUsuario(@PathVariable Long id_usuario,@PathVariable Long id_direccion) {
        
        List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
        List<String> errores = new ArrayList<>();

        
        for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {

            if (usuarioDireccion.getUsuarioIdUsuario().equals(id_usuario) &&
                    usuarioDireccion.getDireccionIdDireccion().equals(id_direccion)) {
                // Si entra aquí es porque ya existe el registro y debemos retornarlo
                Long id_relacion = usuarioDireccion.getId();

                Direccion direc = direccionService.getDireccionById(usuarioDireccion.getDireccionIdDireccion());


                if(userDireccionService.deleteUsuarioDireccion(id_relacion)){
                    errores.add("Direccion eliminada exitosamente con direccion: "+direc.getCalle());
                    return ResponseEntity.ok().body(errores);
                }
                return ResponseEntity.status(409).body(errores);
            }
        }


        return ResponseEntity.badRequest().body(errores);
    }

}
