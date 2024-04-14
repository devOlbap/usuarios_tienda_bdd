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
    public Usuario getUsuarioById(Long id){
        Optional<Usuario> usrOpt = userRepository.findById(id);
        // Verificar si la dirección existe en la base de datos
        if (usrOpt.isPresent()) {
            return usrOpt.get(); // Devolver la dirección si existe
        } else {
            // Manejar el caso en el que la dirección no existe (por ejemplo, lanzar una excepción o devolver null)
            return null; // En este ejemplo, se devuelve null si la dirección no se encuentra
        }
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
    public Usuario getUsuarioByUsername(String username){
        Optional<Usuario> usrOpt = Optional.ofNullable(userRepository.findByUsername(username));

        if (usrOpt.isPresent()) {
            return usrOpt.get(); 
        } else {
            return null; 
        }
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
