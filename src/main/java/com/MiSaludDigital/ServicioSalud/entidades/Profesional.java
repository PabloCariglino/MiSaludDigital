package com.MiSaludDigital.ServicioSalud.entidades;


import java.time.LocalTime;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
//@EqualsAndHashCode(callSuper = false)
public class Profesional{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Long matriculaProfesional;
    private String nombreProfesional;
    private String apellidoProfesional;
    private int edadProfesional;
    private String especialidadProfesional;
    private Long puntuacionProfesional;
    private LocalTime horariosDisponibles;
    private double precioConsulta;
    private String caracteristicaDeOferta; //(telemedicina, presencial, ubicación, obras sociales, datos de contacto).
   // private Boolean estadoProfesional;// baja o alta del profesional en la vista profesional
    
    @OneToMany
    private ArrayList<Turno> turnos;
    
    @OneToMany
    private ArrayList<HistoriaClinica> historiaClinicas;  

    @ManyToOne
    private Paciente paciente;
   
}
