package com.users.usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.usuarios.model.Usuario;
import com.users.usuarios.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public List<Usuario> getUsuarios(){
        return userRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id){
        return userRepository.findById(id);
    }
    @Override
    public Usuario createUsuario(Usuario usuario){

        // Long nuevoId = generarNuevoId();

        // usuario.setId(nuevoId);

        return userRepository.save(usuario);
    }
    @Override
    public Usuario updateUsuario(Long id, Usuario usuario){

        if(userRepository.existsById(id)){
            usuario.setId(id);
            return userRepository.save(usuario);
        }
        return null;
    }
    @Override
    public Boolean deleteUsuario(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para generar un nuevo ID único
    // private Long generarNuevoId() {

    //     Long last_usr_id = userRepository.findMaxId();
    //     // Aquí podrías implementar la lógica para generar un ID único, por ejemplo, consultando el máximo ID en la base de datos y sumándole uno
    //     // En este ejemplo, simplemente se genera un ID aleatorio (para fines demostrativos)
    //     return last_usr_id+1;
    // }
}
