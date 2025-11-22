package com.example.pruebaTecnica.exception;

import java.util.Date;

/**
 *
 * @author rezro
 */
public class ErrorDetails {
    private Date fechaHora;
    private String mensaje;
    private String detalle;

    public ErrorDetails(Date fechaHora, String mensaje, String detalle) {
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDetalle() {
        return detalle;
    }
    
    
}
