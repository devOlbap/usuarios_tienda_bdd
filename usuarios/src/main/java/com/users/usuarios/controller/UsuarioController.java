package com.users.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.users.usuarios.service.UsuarioService;
import com.users.usuarios.model.Usuario;
import com.users.usuarios.model.Login;


import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.ArrayList;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> getUsers(){
        return usuarioService.getUsuarios();
    }
    @GetMapping("/usuarios/{id}")
    public Usuario getUserById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }
    
    @PostMapping("/usuarios/add")
    public ResponseEntity<?> createUser(@RequestBody Usuario usuario) {
        //Lista de errores en validaciones
        List<String> errores = usuario.validarCampos();
        // Si no hay errores, llamar al método del servicio para crear
        if (errores.isEmpty()) {
            return ResponseEntity.ok(usuarioService.createUsuario(usuario));
        }
        
        // Si hay errores, devolver una respuesta con código de error 400 (BadRequest) y la lista de errores
        return ResponseEntity.badRequest().body(errores);
    }
    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody Login log) {
        List<String> mensajes = new ArrayList<>();
        
        Usuario user = usuarioService.getUsuarioByUsername(log.getUsername());

        if(user == null){
            mensajes.add("No existe usuario con el nombre de usuario: "+log.getUsername());
            return ResponseEntity.badRequest().body(mensajes);
        }

        if(user.getPassword().trim().equals(log.getPassword().trim())){
            //Acceso correcto!
            mensajes.add("Bienvenido! Acceso correcto");
            return ResponseEntity.ok(mensajes);
        }
        
        // Si hay mensajes, devolver una respuesta con código de error 400 (BadRequest) y la lista de mensajes
        return ResponseEntity.badRequest().body(mensajes);
    }
    @PutMapping("/usuarios/{id}")
    public Usuario updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        if(usuarioService.deleteUsuario(id)){
            response.put("mensaje", "Usuario eliminado exitosamente con ID: " + id);
            return ResponseEntity.ok().body(response);
        }
        response.put("mensaje", "No existe registro con el ID:"+id);
        return ResponseEntity.badRequest().body(response);
    }


}



