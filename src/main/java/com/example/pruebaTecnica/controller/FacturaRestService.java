package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.model.Factura;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pruebaTecnica.services.Venta;

/**
 *
 * @author rezro
 */
@RestController
@RequestMapping("/api/ventas")
public class FacturaRestService {

    private final Venta ventasService;

    public FacturaRestService(Venta ventasService) {
        this.ventasService = ventasService;
    }

    /**
     * POST: Almacena factura
     * @param identificacionPersona
     * @param factura
     * @return 
     */
    @PostMapping("/personas/{identificacionPersona}/facturas")
    public ResponseEntity<Factura> storeFactura(
            @PathVariable String identificacionPersona,
            @Valid @RequestBody Factura factura) {

        Factura nuevaFactura = ventasService.storeFactura(factura, identificacionPersona);

        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }
    
    /**
     * GET: Encuentra las facturas de una persona
     * @param identificacionPersona
     * @return 
     */
    @GetMapping("/personas/{identificacionPersona}/facturas")
    public ResponseEntity<List<Factura>> findFacturasByPersona(@PathVariable String identificacionPersona) {
        
        List<Factura> facturas = ventasService.findFacturaByIdentificacion(identificacionPersona);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }
}
