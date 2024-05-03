package com.users.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.users.usuarios.model.Usuario;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    
    @Mock
    private UsuarioRepository userRepository;

    @Test
    public void obtenerUsuarioByUsernameTest(){
        Usuario usuario = new Usuario();
        
        usuario.setId(null);
        usuario.setNombre("Gabriel");
        usuario.setApellido("Garrido");
        usuario.setUsername("leirbaG");
        usuario.setPassword("Pass1010");


        when(userRepository.findByUsername("leirbaG")).thenReturn(usuario);

        Usuario userbyUsername = userRepository.findByUsername("leirbaG");

        assertNotNull(userbyUsername);
        assertEquals(userbyUsername.getUsername(), "leirbaG");

    }


}
