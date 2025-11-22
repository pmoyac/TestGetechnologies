package com.example.pruebaTecnica.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author rezro
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PersonaNoEncontradaException.class)
    public ResponseEntity<ErrorDetails> personaNoEncontrada(PersonaNoEncontradaException ex, WebRequest request){
        
        ErrorDetails detalles = new ErrorDetails(
            new Date(), 
            ex.getMessage(), 
            request.getDescription(false)
        );
        // Utiliza el Código HTTP 404
        return new ResponseEntity<>(detalles, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        // Recolectar todos los mensajes de error de la validación
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        String fullMessage = "Errores de validación: " + errors.toString();

        ErrorDetails details = new ErrorDetails(
            new Date(), 
            fullMessage,
            request.getDescription(false)
        );
        // Utiliza el Código HTTP 400
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails(
            new Date(), 
            "Error: " + ex.getMessage(), 
            request.getDescription(false)
        );
        // Utiliza el Código HTTP 500 
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
