package com.users.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.users.usuarios.model.Direccion;
import com.users.usuarios.repository.DireccionRepository;

@ExtendWith(MockitoExtension.class)
public class DireccionServiceTest {

    @InjectMocks
    private DireccionServiceImpl userServiceImpl;

    @Mock
    private DireccionRepository userRepositoryMock;

    @Test
    public void guardarDireccionTest(){
        Direccion direccion = new Direccion();
        
        direccion.setId(null);
        direccion.setCalle("Serrano");
        direccion.setEstado((char)1);
        direccion.setNumeracion("Sitio 3 letra C");
        direccion.setOtro("casa 1");

        when(userRepositoryMock.save(any())).thenReturn(direccion);

        Direccion dir = userServiceImpl.createDireccion(direccion);

        assertEquals("Serrano", dir.getCalle());

    }


}