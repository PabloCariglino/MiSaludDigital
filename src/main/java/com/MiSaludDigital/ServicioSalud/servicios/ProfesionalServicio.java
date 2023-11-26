package com.MiSaludDigital.ServicioSalud.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.entidades.Profesional;
import com.MiSaludDigital.ServicioSalud.repositorios.ProfesionalRepositorio;

@Service
public class ProfesionalServicio {

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    //LISTAR PROFESIONALES
    public List<Profesional> listaProfesionales() {

        return (List<Profesional>) profesionalRepositorio.findAll();
    }

    //GUARDAR PROFESIONALES
    public void guardar(Profesional profesional) {

        profesionalRepositorio.save(profesional);
    }

    //ELIMINAR PROFESIONALES
    public void eliminar(Profesional profesional) {

        profesionalRepositorio.delete(profesional);
    }

    //BUSCAR PROFESIONAL POR ID
    public Profesional buscarProfesional(Profesional profesional) {

        return profesionalRepositorio.findById(profesional.getMatriculaProfesional()).orElse(null);
    }
}
