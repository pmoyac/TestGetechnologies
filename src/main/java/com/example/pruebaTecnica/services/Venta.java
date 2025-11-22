package com.example.pruebaTecnica.services;

import com.example.pruebaTecnica.model.Factura;
import com.example.pruebaTecnica.model.Persona;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.pruebaTecnica.repository.FacturaRepository;

/**
 *
 * @author rezro
 */
@Service
public class Venta {

    public static final Logger log = LoggerFactory.getLogger(Venta.class);

    private final FacturaRepository facturaRepository;
    private final Directorio directorio;

    public Venta(FacturaRepository facturaRepository, Directorio directorio) {
        this.facturaRepository = facturaRepository;
        this.directorio = directorio;
    }

    /**
     * Busca todas las facturas de una persona
     *
     * @param identificacionPersona
     * @return
     */
    public List<Factura> findFacturaByIdentificacion(String identificacionPersona) {
        log.debug("Buscando facturas por persona con identificacion: {}", identificacionPersona);

        Persona persona = directorio.findPersonaByIdentificacion(identificacionPersona);

        return facturaRepository.findByPersona_Id(persona.getId());
    }

    /**
     * Metodo para asociar persona a factura y guardarla
     *
     * @param factura
     * @param identificacionPersona
     * @return
     */
    @Transactional
    public Factura storeFactura(Factura factura, String identificacionPersona) {
        log.info("Almacenando factura para persona: {}", identificacionPersona);

        Persona persona = directorio.findPersonaByIdentificacion(identificacionPersona);

        factura.setPersona(persona);

        return facturaRepository.save(factura);
    }
}
