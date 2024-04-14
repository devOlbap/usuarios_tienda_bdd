package com.users.usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.usuarios.model.UsuarioRol;

import com.users.usuarios.repository.UsuarioRolRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

    @Autowired
    private UsuarioRolRepository userRolRepository;

    @Override
    public List<UsuarioRol> getUsuarioRoles(){
        return userRolRepository.findAll();
    }
    
    @Override
    public Optional<UsuarioRol> getUsuarioRolById(Long id){
        return userRolRepository.findById(id);
    }
    @Override
    public UsuarioRol createUsuarioRol(UsuarioRol usuario_Rol){
        return userRolRepository.save(usuario_Rol);
    }
    
    @Override
    public UsuarioRol updateUsuarioRol(Long id, UsuarioRol user_direc){

        if(userRolRepository.existsById(id)){
            user_direc.setId(id);
            return userRolRepository.save(user_direc);
        }
        return null;
    }
    @Override
    public Boolean deleteUsuarioRol(Long id){
        if(userRolRepository.existsById(id)){
            userRolRepository.deleteById(id);
            return true;
        }
        return false;
    }

    
    
}