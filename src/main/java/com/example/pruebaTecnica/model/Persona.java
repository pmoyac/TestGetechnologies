package com.example.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rezro
 */
@Entity
@Data
@NoArgsConstructor
public class Persona {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    @NotBlank(message = "La identificación es necesaria")
    private String identificacion;
    
    @JsonIgnore
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();
    
    public void agregarFactura(Factura factura){
        facturas.add(factura);
        factura.setPersona(this);
    }
    
    
}
