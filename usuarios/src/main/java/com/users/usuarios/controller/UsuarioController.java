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

import java.util.HashMap;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsers(){
        return usuarioService.getUsuarios();
    }
    @GetMapping("/{id}")
    public Usuario getUserById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }
    
     @PostMapping("/add")
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
    @PutMapping("/{id}")
    public Usuario updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }
    
    @DeleteMapping("/{id}")
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



