package edu.unc.pereyramonzon.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idReserva")


public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    
    // Otros atributos de Reserva
    
    private Date fechaInicio;
    private Date fechaFinal;
    private Double precioTotal;
    private Boolean entregado;
    
    @ManyToMany
    @JoinTable(name = "auto_reserva",
	joinColumns = @JoinColumn(name = "idReserva"),
	inverseJoinColumns = @JoinColumn(name = "idAuto"))
    private List<Auto> autos = new ArrayList<>();

    @ManyToOne
    private Agencia agencia; // Relación con Agencia
    
    @ManyToOne
    @JsonBackReference
    private Cliente cliente; // Relación con Cliente
    

    
    // Getters y setters
}
