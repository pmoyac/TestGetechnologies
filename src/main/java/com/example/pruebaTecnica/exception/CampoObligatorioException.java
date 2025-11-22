package com.example.pruebaTecnica.exception;

/**
 *
 * @author rezro
 */
public class CampoObligatorioException extends RuntimeException{
    
    public CampoObligatorioException (String message){
        super(message);
    }
}
