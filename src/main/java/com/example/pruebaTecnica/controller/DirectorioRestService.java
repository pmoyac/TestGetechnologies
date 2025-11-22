package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.model.Persona;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pruebaTecnica.services.Directorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author rezro
 */
@RestController
@RequestMapping("/api/directorio/personas")
public class DirectorioRestService {
    
    private final Directorio directorioService;

    public DirectorioRestService(Directorio directorioService) {
        this.directorioService = directorioService;
    }
    
    /**
     * POST: Almacena una nueva persona
     * @param persona
     * @return 
     */
    @PostMapping
    public ResponseEntity<Persona> storePersona(@Valid @RequestBody Persona persona){
        Persona nuevaPersona = directorioService.storePersona(persona);
        
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }
    
    /**
     * GET: busca todas las personas
     * @param pageable
     * @return 
     */
    @GetMapping
    public ResponseEntity<Page<Persona>> findPersonas(Pageable pageable){
        Page<Persona> persona = directorioService.findPersonas(pageable);
        
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }
    
    @GetMapping("/{identificacion}")
    public ResponseEntity<Persona> findPersonaByIdentificacion(@PathVariable String identificacion){
        
        Persona persona = directorioService.findPersonaByIdentificacion(identificacion);
        
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }
    
    /**
     * DELETE: Elimina una persona y todas sus facturas por identificacion 
     * @param identificacion
     * @return 
     */
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> deletePersonaByIdentificacion(@PathVariable String identificacion){
        
        directorioService.deletePersonaByIdentificacion(identificacion);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
    
    
    
}
