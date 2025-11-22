package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.model.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rezro
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    
    /**
     * Busca una persona por su identificación
     * @param identificacion
     * @return 
     */
    Optional<Persona> findByIdentificacion(String identificacion);
    
}
