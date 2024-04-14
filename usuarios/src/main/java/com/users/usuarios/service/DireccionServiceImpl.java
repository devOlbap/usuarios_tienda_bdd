package com.users.usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.usuarios.model.Direccion;
import com.users.usuarios.repository.DireccionRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public List<Direccion> getDirecciones(){
        return direccionRepository.findAll();
    }

    @Override
    public Direccion getDireccionById(Long id){
        Optional<Direccion> dirOptional = direccionRepository.findById(id);
        // Verificar si la dirección existe en la base de datos
        if (dirOptional.isPresent()) {
            return dirOptional.get(); // Devolver la dirección si existe
        } else {
            // Manejar el caso en el que la dirección no existe (por ejemplo, lanzar una excepción o devolver null)
            return null; // En este ejemplo, se devuelve null si la dirección no se encuentra
        }
    }
    @Override
    public Direccion createDireccion(Direccion dir){

        // Long nuevoId = generarNuevoId();

        // usuario.setId(nuevoId);

        return direccionRepository.save(dir);
    }
    @Override
    public Direccion updateDireccion(Long id, Direccion dir){

        if(direccionRepository.existsById(id)){
            dir.setId(id);
            return direccionRepository.save(dir);
        }
        return null;
    }
    @Override
    public Boolean deleteDireccion(Long id){
        if(direccionRepository.existsById(id)){
            direccionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para generar un nuevo ID único
    // private Long generarNuevoId() {

    //     Long last_usr_id = direccionRepository.findMaxId();
    //     // Aquí podrías implementar la lógica para generar un ID único, por ejemplo, consultando el máximo ID en la base de datos y sumándole uno
    //     // En este ejemplo, simplemente se genera un ID aleatorio (para fines demostrativos)
    //     return last_usr_id+1;
    // }
}