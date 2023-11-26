package com.MiSaludDigital.ServicioSalud.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiSaludDigital.ServicioSalud.entidades.HistoriaClinica;

@Repository
public interface HistoriaClinicaRepositorio extends JpaRepository<HistoriaClinica,Long>{
    
    
}
