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

import com.users.usuarios.service.UsuarioRolService;
import com.users.usuarios.service.UsuarioService;
import com.users.usuarios.service.RolService;

import com.users.usuarios.model.UsuarioRol;
import com.users.usuarios.model.Rol;
import com.users.usuarios.model.Usuario;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/usuarios")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolService userRolService;

    @Autowired
    private RolService rolService;


    @Autowired
    private UsuarioService userService;

    @GetMapping("/{id_usuario}/roles")
    public ResponseEntity<List<Rol>> getRolesByUser(@PathVariable Long id_usuario){
        List<UsuarioRol> usuarioRoles = userRolService.getUsuarioRoles();
        List<Rol> roles = new ArrayList<>();

        for (UsuarioRol usuarioRol : usuarioRoles) {
            if(id_usuario == usuarioRol.getUsuarioIdUsuario()){
                Rol rol = rolService.getRolById(usuarioRol.getRolIdRol());
                roles.add(rol);
            }
            
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id_usuario}/roles/{id_rol}")
    public Rol getRolUserById(@PathVariable Long id_usuario,@PathVariable Long id_rol ) {
        List<UsuarioRol> usuarioRoles = userRolService.getUsuarioRoles();
        Rol rol = new Rol();

        for (UsuarioRol usuarioRol : usuarioRoles) {
            if(id_usuario == usuarioRol.getUsuarioIdUsuario() && id_rol == usuarioRol.getRolIdRol()){
                rol = rolService.getRolById(id_rol);
            }
        }

        return rol;
   }

    //GET LISTOS

    /*
        traté de crear un DireccionRequest para recibir un JSON custom pero cuando queria recibir el id
        de la direccion ... lo tomaba como 0 
        Pero si le quito el cuerpo y envio el numero solo... lo toma bien lo 
        tengo que convertirlo en Long para validar el dato
    */
    // @PostMapping("/{id_usuario}/direcciones/add")
    // public ResponseEntity<?> postDireccionUsuario(@PathVariable Long id_usuario, @RequestBody int dir) {
        
    //     List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
    //     long maxId = Long.MIN_VALUE;
    //     List<String> errores = new ArrayList<>();

    //     // int id_direccion = dir.getIdDireccion(); //DireccionRequest.method()

    //     Long aux = Long.valueOf(dir);

    //     Direccion direccion_nueva = direccionService.getDireccionById(aux);

    //     Usuario user = userService.getUsuarioById(id_usuario);

    //     if(user == null){
    //         errores.add("Error al buscar usuario.");
    //         return ResponseEntity.badRequest().body(errores);
    //     }
    //     if (direccion_nueva == null) {
    //         //Si la dirección no existe, agregar un mensaje de error a la lista
    //         errores.add("Error al obtener la dirección");
    //         return ResponseEntity.badRequest().body(errores);
    //     }

    //     for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {
    //         long id = usuarioDireccion.getId();

    //         if (id > maxId) {
    //             maxId = id;
    //         }
    //         if (usuarioDireccion.getUsuarioIdUsuario().equals(id_usuario) &&
    //                 usuarioDireccion.getDireccionIdDireccion().equals(direccion_nueva.getId())) {
    //             // Si entra aquí es porque ya existe el registro y debemos retornarlo
    //             errores.add("Ya existe registro del usuario: "+id_usuario+" con la direccion: "+direccion_nueva.getCalle());
    //             return ResponseEntity.status(409).body(errores);
    //         }
    //     }

    //     UsuarioDireccion userDireccion = new UsuarioDireccion();

    //     userDireccion.setId(maxId+1);
    //     userDireccion.setUsuarioIdUsuario(id_usuario);
    //     userDireccion.setDireccionIdDireccion(direccion_nueva.getId());

    //     userDireccionService.createUsuarioDireccion(userDireccion);

    //     return ResponseEntity.ok(direccion_nueva);
    // }

    // @DeleteMapping("/{id_usuario}/direcciones/{id_direccion}")
    // public ResponseEntity<?> deleteDireccionUsuario(@PathVariable Long id_usuario,@PathVariable Long id_direccion) {
        
    //     List<UsuarioDireccion> usuarioDirecciones = userDireccionService.getUsuarioDirecciones();
    //     List<String> errores = new ArrayList<>();

        
    //     for (UsuarioDireccion usuarioDireccion : usuarioDirecciones) {

    //         if (usuarioDireccion.getUsuarioIdUsuario().equals(id_usuario) &&
    //                 usuarioDireccion.getDireccionIdDireccion().equals(id_direccion)) {
    //             // Si entra aquí es porque ya existe el registro y debemos retornarlo
    //             Long id_relacion = usuarioDireccion.getId();

    //             Direccion direc = direccionService.getDireccionById(usuarioDireccion.getDireccionIdDireccion());


    //             if(userDireccionService.deleteUsuarioDireccion(id_relacion)){
    //                 errores.add("Direccion eliminada exitosamente con direccion: "+direc.getCalle());
    //                 return ResponseEntity.ok().body(errores);
    //             }
    //             return ResponseEntity.status(409).body(errores);
    //         }
    //     }


    //     return ResponseEntity.badRequest().body(errores);
    // }

}
