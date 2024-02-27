package edu.unc.pereyramonzon.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity 	
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idCli")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCli;
    
    @Column(unique = true)
    private String dni;
    
    private String nombre;
    private String direccion;
    private String telefono;
    
    @ManyToOne
    @JoinColumn(name = "idClienteAvalador")
    private Cliente clienteAvalador;
    
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Reserva> reservas = new ArrayList<>();
}
