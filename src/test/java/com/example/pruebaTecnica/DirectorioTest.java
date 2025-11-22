package com.example.pruebaTecnica;

import com.example.pruebaTecnica.exception.PersonaNoEncontradaException;
import com.example.pruebaTecnica.model.Persona;
import com.example.pruebaTecnica.repository.PersonaRepository;
import com.example.pruebaTecnica.services.Directorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author rezro
 */
@ExtendWith(MockitoExtension.class)
public class DirectorioTest {

    @Mock
    private PersonaRepository personaRepository;


    @InjectMocks
    private Directorio directorio;

    private Persona personaValida;


    @BeforeEach
    void setUp() {
        personaValida = new Persona();
        personaValida.setId(1L);
        personaValida.setIdentificacion("ABC789");
        personaValida.setNombre("Carlos");
        personaValida.setApellidoPaterno("Soto");
    }
    
    @Test
    void storePersona_debeRetornarPersonaGuardada() {
  
        when(personaRepository.save(any(Persona.class))).thenReturn(personaValida);

   
        Persona resultado = directorio.storePersona(personaValida);

     
        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
        

        verify(personaRepository, times(1)).save(any(Persona.class));
    }
    
    @Test
    void findPersonaByIdentificacion_debeRetornarPersona() {
    
        when(personaRepository.findByIdentificacion("ABC789")).thenReturn(Optional.of(personaValida));

       
        Persona resultado = directorio.findPersonaByIdentificacion("ABC789");

   
        assertNotNull(resultado);
        assertEquals("ABC789", resultado.getIdentificacion());
    }
    
    @Test
    void deletePersonaByIdentificacion_debeLanzarExcepcionSiNoExiste() {
        final String idInexistente = "ID_FAIL";
        
   
        when(personaRepository.findByIdentificacion(idInexistente)).thenReturn(Optional.empty());

       
        assertThrows(PersonaNoEncontradaException.class, () -> {
            directorio.deletePersonaByIdentificacion(idInexistente);
        });
        
 
        verify(personaRepository, never()).delete(any(Persona.class));
    }
}
