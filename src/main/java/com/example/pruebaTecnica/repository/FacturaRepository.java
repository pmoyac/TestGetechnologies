package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.model.Factura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rezro
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{
    
    /**
     * Busca todas las facturas de una persona por su id
     * @param personaId
     * @return 
     */
    List<Factura> findByPersona_Id(Long personaId);
    
}
