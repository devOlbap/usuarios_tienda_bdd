package com.users.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.users.usuarios.model.Direccion;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DireccionRepositoryTest {
    
    @Mock
    private DireccionRepository direccionRepository;

    @Test
    public void obtenerDireccionByIdTest(){
        Direccion direc = new Direccion();
        
        direc.setId(null);
        direc.setCalle("SERRANO");
        direc.setNumeracion("12333");
        direc.setOtro("Dpto 2312");

        when(direccionRepository.findByCalle("SERRANO")).thenReturn(direc);

        Direccion direccion_mock = direccionRepository.findByCalle("SERRANO");

        assertNotNull(direccion_mock);
        assertEquals(direccion_mock.getCalle(), "SERRANO");

    }


}
