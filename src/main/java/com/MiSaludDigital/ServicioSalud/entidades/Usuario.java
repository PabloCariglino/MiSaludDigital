package com.MiSaludDigital.ServicioSalud.entidades;

import com.MiSaludDigital.ServicioSalud.enumeraciones.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreUsuario;
    private String password;
    private String email;
    private Boolean estadoUsuario;

    @OneToOne
    private Profesional profesional;

    @Enumerated()
    private Rol rol;

    @OneToOne
    private Imagen imagen;
}
