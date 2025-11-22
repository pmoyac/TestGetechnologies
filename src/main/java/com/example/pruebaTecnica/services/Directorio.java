package com.example.pruebaTecnica.services;

import com.example.pruebaTecnica.model.Persona;
import com.example.pruebaTecnica.exception.PersonaNoEncontradaException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.pruebaTecnica.repository.PersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author rezro
 */
@Service
public class Directorio {

    private static final Logger log = LoggerFactory.getLogger(Directorio.class);

    private final PersonaRepository personaRepository;

    //extends
    public Directorio(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    /**
     * Metodo para encontrar persona por su identificacion
     *
     * @param identificacion
     * @return
     */
    public Persona findPersonaByIdentificacion(String identificacion) {
        log.debug("Buscando persona por identificacion: {}", identificacion);

        return personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> {
                    log.error("Persona con identificación: {} no encontrada", identificacion);
                    return new PersonaNoEncontradaException("Persona con identificación " + identificacion + " no encontrada.");
                });
    }

    /**
     * Metodo que devuelve todas las personas
     *
     * @param pageable
     * @return
     */
    public Page<Persona> findPersonas(Pageable pageable) {
        log.info("Consultando todas las personas: Página {}, Tamaño {}", 
                 pageable.getPageNumber(), 
                 pageable.getPageSize());
        return personaRepository.findAll(pageable);
    }

    /**
     * Metodo para eliminar una persona y sus facturas por su identificacion
     *
     * @param identificacion
     */
    public void deletePersonaByIdentificacion(String identificacion) {
        log.warn("Borrando persona con identificacion: {}", identificacion);

        Persona persona = findPersonaByIdentificacion(identificacion);

        personaRepository.delete(persona);

        log.warn("Persona y facturas borradas con exito");
    }

    /**
     * Metodo para almacenar una persona
     *
     * @param persona
     * @return
     */
    public Persona storePersona(Persona persona) {
        log.info("Almacenando persona: {}", persona.getIdentificacion());

        return personaRepository.save(persona);
    }

}
