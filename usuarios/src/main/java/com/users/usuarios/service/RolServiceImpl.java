package com.users.usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.usuarios.model.Rol;
import com.users.usuarios.repository.RolRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }

    @Override
    public Rol getRolById(Long id){
        Optional<Rol> rolOP = rolRepository.findById(id);
        if (rolOP.isPresent()) {
            return rolOP.get(); 
        } else {
            return null; 
        }
    }
    @Override
    public Rol createRol(Rol rol){
        return rolRepository.save(rol);
    }
    @Override
    public Rol updateRol(Long id, Rol rol){

        if(rolRepository.existsById(id)){
            rol.setId(id);
            return rolRepository.save(rol);
        }
        return null;
    }
    @Override
    public Boolean deleteRol(Long id){
        if(rolRepository.existsById(id)){
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para generar un nuevo ID único
    // private Long generarNuevoId() {

    //     Long last_usr_id = rolRepository.findMaxId();
    //     // Aquí podrías implementar la lógica para generar un ID único, por ejemplo, consultando el máximo ID en la base de datos y sumándole uno
    //     // En este ejemplo, simplemente se genera un ID aleatorio (para fines demostrativos)
    //     return last_usr_id+1;
    // }
}
