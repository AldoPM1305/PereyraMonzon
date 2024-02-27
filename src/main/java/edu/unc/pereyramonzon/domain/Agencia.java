package edu.unc.pereyramonzon.domain;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idAgencia")


public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgencia;
    
    private String nombre;
    private String ubicacion;
    private String telefono;
    
    @OneToMany(mappedBy = "agencia")
    
    private List<Reserva> reservas = new ArrayList<>();
}