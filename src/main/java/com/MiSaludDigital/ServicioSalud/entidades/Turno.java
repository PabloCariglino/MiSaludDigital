package com.MiSaludDigital.ServicioSalud.entidades;

import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private LocalTime hora;
    private Boolean estadoTurno;

    @ManyToOne
    private Paciente paciente;  // Relación con Paciente

    @ManyToOne
    private Profesional profesional;  // Relación con Profesional.
}
