package com.MiSaludDigital.ServicioSalud.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.repositorios.HistoriaClinicaRepositorio;

@Service
public class HistoriaClinicaServicio {

    @Autowired
    HistoriaClinicaRepositorio historiaClinicaRepositorio;

    // BUSCAR HISTORIA CLINICA POR ID
    //public HistoriaClinica buscarHistoriaClinicaPorID(Long id) {

     //   Optional<HistoriaClinica> respuesta = historiaClinicaRepositorio.
      //  HistoriaClinica historiaClinica = respuesta.get();
       // return historiaClinica;
    //}


  
}
