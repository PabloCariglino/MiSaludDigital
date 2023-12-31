package com.MiSaludDigital.ServicioSalud.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.entidades.HistoriaClinica;
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


    public List<HistoriaClinica> listarHistoriasClinicas() {
        List<HistoriaClinica> listaHC;
    
        try {
            listaHC = historiaClinicaRepositorio.findAll();
    
            // Manejar el caso en que la lista sea null
            if (listaHC == null) {
                listaHC = new ArrayList<>();
            }
    
        } catch (Exception ex) {
            
            ex.printStackTrace();
            listaHC = new ArrayList<>(); 
        }
    
        return listaHC;
    }
}
