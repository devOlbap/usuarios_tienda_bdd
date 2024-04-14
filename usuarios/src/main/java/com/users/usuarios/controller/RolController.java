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

import com.users.usuarios.service.RolService;
import com.users.usuarios.model.Rol;

import java.util.HashMap;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> getUsers(){
        return rolService.getRoles();
    }
    @GetMapping("/{id}")
    public Rol getRolById(@PathVariable Long id) {
        return rolService.getRolById(id);
    }
    
     @PostMapping("/add")
    public ResponseEntity<?> createRol(@RequestBody Rol rol) {
        
        //Lista de errores en validaciones
        List<String> errores = rol.validarCampos();


        // Si no hay errores, llamar al método del servicio para crear
        if (errores.isEmpty()) {
            return ResponseEntity.ok(rolService.createRol(rol));
        }
        
        // Si hay errores, devolver una respuesta con código de error 400 (BadRequest) y la lista de errores
        return ResponseEntity.badRequest().body(errores);

    }
    @PutMapping("/{id}")
    public Rol updateRol(@PathVariable Long id, @RequestBody Rol rol) {
        return rolService.updateRol(id, rol);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delRol(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        if(rolService.deleteRol(id)){
            response.put("mensaje", "Rol eliminado exitosamente con ID: " + id);
            return ResponseEntity.ok().body(response);
        }
        response.put("mensaje", "No existe registro con el ID:"+id);
        return ResponseEntity.badRequest().body(response);
    }


}