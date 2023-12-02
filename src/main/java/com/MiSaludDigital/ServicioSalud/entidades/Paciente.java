package com.MiSaludDigital.ServicioSalud.entidades;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Paciente{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") //Estrategia alternativa
    private String id;
    private Long dniPaciente;
    private String nombrePaciente;
    private String ApellidoPaciente;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoPaciente;
    private String obraSocial;
    private Double telContacto;
    private String intencionConsulta;
    
    @OneToOne
    private HistoriaClinica historiaClinicas;  
    
    @OneToMany
    private ArrayList<Turno> turnos;
    
    @OneToMany
    private ArrayList<Profesional> profesional;
}
