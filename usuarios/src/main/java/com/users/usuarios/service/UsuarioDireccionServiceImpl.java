package com.users.usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.usuarios.model.UsuarioDireccion;

import com.users.usuarios.repository.UsuarioDireccionRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioDireccionServiceImpl implements UsuarioDireccionService {

    @Autowired
    private UsuarioDireccionRepository userDireccionRepository;

    @Override
    public List<UsuarioDireccion> getUsuarioDirecciones(){
        return userDireccionRepository.findAll();
    }
    
    @Override
    public Optional<UsuarioDireccion> getUsuarioDireccionById(Long id){
        return userDireccionRepository.findById(id);
    }
    @Override
    public UsuarioDireccion createUsuarioDireccion(UsuarioDireccion usuario_direccion){
        return userDireccionRepository.save(usuario_direccion);
    }
    
    @Override
    public UsuarioDireccion updateUsuarioDireccion(Long id, UsuarioDireccion user_direc){

        if(userDireccionRepository.existsById(id)){
            user_direc.setId(id);
            return userDireccionRepository.save(user_direc);
        }
        return null;
    }
    @Override
    public Boolean deleteUsuarioDireccion(Long id){
        if(userDireccionRepository.existsById(id)){
            userDireccionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    
    
}
