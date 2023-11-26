package com.MiSaludDigital.ServicioSalud.entidades;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Paciente {
    
    @Id 
    private Long dniPaciente;
    
    private String nombrePaciente;
    private String ApellidoPaciente;
    private Date fechaNacimientoPaciente;
    private String obraSocial;
    private int telContacto;
    private String intencionConsulta;
    
    @OneToOne
    private HistoriaClinica historiaClinicas;  
    
    @OneToMany
    private ArrayList<Turno> turnos;
    
    @OneToMany
    private ArrayList<Profesional> profesional;
}
