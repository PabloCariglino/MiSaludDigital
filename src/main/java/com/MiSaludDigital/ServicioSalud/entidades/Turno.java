package com.MiSaludDigital.ServicioSalud.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaYHora;
    private Boolean estadoTurno;

    @ManyToOne
    private Paciente paciente;  // Relación con Paciente

    @ManyToOne
    private Profesional profesional;  // Relación con Profesional
}
