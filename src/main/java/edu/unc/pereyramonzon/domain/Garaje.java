package edu.unc.pereyramonzon.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Garaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGaraje;
    private String nombre;
    private String ubicacion;
    
    
    @OneToMany(mappedBy = "garaje")
    @JsonManagedReference
    private List<Auto> autos = new ArrayList<>();
}