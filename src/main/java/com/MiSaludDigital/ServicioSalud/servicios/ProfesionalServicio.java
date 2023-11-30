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

    public Profesional crearProfesional(Long matriculaProfesional,String nombreProfesional, String apellidoProfesional, int edadProfesional,
            String especialidadProfesional, Long puntuacionProfesional,
            double precioConsulta) {

        Profesional profesional = new Profesional();
        profesional.setMatriculaProfesional(matriculaProfesional);
        profesional.setNombreProfesional(nombreProfesional);
        profesional.setApellidoProfesional(apellidoProfesional);
        profesional.setEdadProfesional(edadProfesional);
        profesional.setEspecialidadProfesional(especialidadProfesional);
        profesional.setPuntuacionProfesional(puntuacionProfesional);
        // profesional.setHorariosDisponibles(horariosDisponibles); // Agregar ARRIBA!
        profesional.setPrecioConsulta(precioConsulta);
        return profesionalRepositorio.save(profesional);
    }

    // LISTAR PROFESIONALES
    public List<Profesional> listaProfesionales() {

        return (List<Profesional>) profesionalRepositorio.findAll();
    }

    // GUARDAR PROFESIONALES
    public void guardar(Profesional profesional) {

        profesionalRepositorio.save(profesional);
    }

    // ELIMINAR PROFESIONALES
    public void eliminar(Profesional profesional) {

        profesionalRepositorio.delete(profesional);
    }

    // BUSCAR PROFESIONAL POR ID
    public Profesional buscarProfesional(Profesional profesional) {

        return profesionalRepositorio.findById(profesional.getMatriculaProfesional()).orElse(null);
    }
}
