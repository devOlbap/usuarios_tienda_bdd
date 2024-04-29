package com.users.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.users.usuarios.model.Usuario;
import com.users.usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioServiceImpl userServiceImpl;

    @Mock
    private UsuarioRepository userRepositoryMock;

    @Test
    public void guardarUsuarioTest(){
        Usuario usuario = new Usuario();
        
        usuario.setId(null);
        usuario.setNombre("Gabriel");
        usuario.setApellido("Garrido");
        usuario.setUsername("leirbaG");
        usuario.setPassword("Pass1010");


        when(userRepositoryMock.save(any())).thenReturn(usuario);

        Usuario usr_res = userServiceImpl.createUsuario(usuario);

        assertEquals("Gabriel", usr_res.getNombre());

    }


}