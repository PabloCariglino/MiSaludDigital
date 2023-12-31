package com.MiSaludDigital.ServicioSalud.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiSaludDigital.ServicioSalud.entidades.Turno;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno,Long>{
    List<Turno> findByPacienteId(Long pacienteId);
    List<Turno> findByProfesionalId(Long profesionalId);
}
