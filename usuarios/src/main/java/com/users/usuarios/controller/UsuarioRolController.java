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

    @PostMapping("/{id_usuario}/roles/add")
    public ResponseEntity<?> postRolUsuario(@PathVariable Long id_usuario, @RequestBody int rol) {
        
        List<UsuarioRol> usuarioRoles = userRolService.getUsuarioRoles();
        long maxId = Long.MIN_VALUE;
        List<String> errores = new ArrayList<>();

        //int id_Rol = dir.getIdRol(); //RolRequest.method()

        Long aux = Long.valueOf(rol);

        Rol rol_nueva = rolService.getRolById(aux);

        Usuario user = userService.getUsuarioById(id_usuario);

        if(user == null){
            errores.add("Error al buscar usuario.");
            return ResponseEntity.badRequest().body(errores);
        }
        if (rol_nueva == null) {
            //Si la dirección no existe, agregar un mensaje de error a la lista
            errores.add("Error al obtener el Rol");
            return ResponseEntity.badRequest().body(errores);
        }

        for (UsuarioRol usuarioRol : usuarioRoles) {
            long id = usuarioRol.getId();

            if (id > maxId) {
                maxId = id;
            }
            if (usuarioRol.getUsuarioIdUsuario().equals(id_usuario) &&
                    usuarioRol.getRolIdRol().equals(rol_nueva.getId())) {
                //Si entra aquí es porque ya existe el registro y debemos retornarlo
                errores.add("Ya existe registro del usuario: "+user.getFullName()+" con el Rol: "+rol_nueva.getDescripcion());
                return ResponseEntity.status(409).body(errores);
            }
        }

        UsuarioRol userRol = new UsuarioRol();

        userRol.setId(maxId+1);
        userRol.setUsuarioIdUsuario(id_usuario);
        userRol.setRolIdRol(rol_nueva.getId());

        userRolService.createUsuarioRol(userRol);

        return ResponseEntity.ok(rol_nueva);
    }

    @DeleteMapping("/{id_usuario}/roles/{id_rol}")
    public ResponseEntity<?> deleteDireccionUsuario(@PathVariable Long id_usuario,@PathVariable Long id_rol) {
        
        List<UsuarioRol> usuarioRoles = userRolService.getUsuarioRoles();
        List<String> errores = new ArrayList<>();

        Usuario user = userService.getUsuarioById(id_usuario);

        for (UsuarioRol usuarioRol : usuarioRoles) {

            if (usuarioRol.getUsuarioIdUsuario().equals(id_usuario) &&
                    usuarioRol.getRolIdRol().equals(id_rol)) {
                //Si entra aquí es porque ya existe el registro y debemos retornarlo
                Long id_relacion = usuarioRol.getId();

                Rol rol = rolService.getRolById(usuarioRol.getRolIdRol());


                if(userRolService.deleteUsuarioRol(id_relacion)){
                    errores.add("Rol de usuario: "+user.getFullName()+". Eliminado exitosamente con Rol: "+rol.getDescripcion());
                    return ResponseEntity.ok().body(errores);
                }
                return ResponseEntity.status(409).body(errores);
            }
        }


        return ResponseEntity.badRequest().body(errores);
    }

}
