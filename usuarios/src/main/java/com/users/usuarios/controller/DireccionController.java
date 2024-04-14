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

import com.users.usuarios.service.DireccionService;
import com.users.usuarios.model.Direccion;

import java.util.HashMap;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public List<Direccion> getUsers(){
        return direccionService.getDirecciones();
    }
    @GetMapping("/{id}")
    public Direccion getUserById(@PathVariable Long id) {
        return direccionService.getDireccionById(id);
    }
    
     @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody Direccion Direccion) {
        
        //Lista de errores en validaciones
        List<String> errores = Direccion.validarCampos();


        // Si no hay errores, llamar al método del servicio para crear
        if (errores.isEmpty()) {
            return ResponseEntity.ok(direccionService.createDireccion(Direccion));
        }
        
        // Si hay errores, devolver una respuesta con código de error 400 (BadRequest) y la lista de errores
        return ResponseEntity.badRequest().body(errores);

    }
    @PutMapping("/{id}")
    public Direccion updateDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        return direccionService.updateDireccion(id, direccion);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDireccion(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        if(direccionService.deleteDireccion(id)){
            response.put("mensaje", "Direccion eliminada exitosamente con ID: " + id);
            return ResponseEntity.ok().body(response);
        }
        response.put("mensaje", "No existe registro con el ID:"+id);
        return ResponseEntity.badRequest().body(response);
    }


}