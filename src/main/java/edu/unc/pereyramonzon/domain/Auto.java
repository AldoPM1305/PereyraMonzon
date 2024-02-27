package edu.unc.pereyramonzon.domain;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idAuto")
public class Auto {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idAuto;
	
	@Column(unique=true)
	private String matricula;
	
	private String modelo;
	private String color;
	private String marca;
	
	private Double precioReserva;
	private Double precioLtGasolina;
	
	//Relacion con garage
	@ManyToOne
	@JsonBackReference
	private Garaje garaje;
	
	//relacion muchos a muchos con reserva
	@ManyToMany
	private List<Reserva> reservas=new ArrayList<>();
}