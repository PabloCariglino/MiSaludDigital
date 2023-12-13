package com.MiSaludDigital.ServicioSalud.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiSaludDigital.ServicioSalud.entidades.Profesional;



@Repository
public interface ProfesionalRepositorio extends JpaRepository<Profesional,Long>{
    
    Optional<Profesional> findByMatriculaProfesional(Long matriculaProfesional);
}

