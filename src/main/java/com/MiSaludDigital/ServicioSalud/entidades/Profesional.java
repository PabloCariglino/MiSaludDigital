package com.MiSaludDigital.ServicioSalud.entidades;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// @EqualsAndHashCode(callSuper = false)
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matriculaProfesional;
    private String nombreProfesional;
    private String apellidoProfesional;
    private int edadProfesional;
    private String especialidadProfesional;
    private Long puntuacionProfesional;
    private LocalTime horariosDisponibles;
    private double precioConsulta;
    private String caracteristicaDeOferta; // (telemedicina, presencial, ubicación, obras sociales, datos de contacto).

    // @OneToMany
    // private List<Turno> turnos;

    @OneToMany
    private List<HistoriaClinica> historiaClinicas;

    @ManyToOne
    private Paciente paciente;

}
