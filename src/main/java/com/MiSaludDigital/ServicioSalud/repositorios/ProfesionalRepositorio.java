package com.MiSaludDigital.ServicioSalud.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiSaludDigital.ServicioSalud.entidades.Profesional;


@Repository
public interface ProfesionalRepositorio extends JpaRepository<Profesional,Long>{
    
    
}

