package com.users.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.users.usuarios.model.Usuario;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UsuarioRepository userRepository;

    @Test
    public void obtenerUsuarioByUsernameTest(){

        Usuario userbyUsername = userRepository.findByUsername("admin");

        assertNotNull(userbyUsername.getId());
        assertEquals(userbyUsername.getUsername(), "admin");

    }


}
