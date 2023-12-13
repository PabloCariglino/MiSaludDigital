package com.MiSaludDigital.ServicioSalud.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.entidades.Profesional;
import com.MiSaludDigital.ServicioSalud.repositorios.ProfesionalRepositorio;

@Service
public class ProfesionalServicio {

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;


    // CREAR PROFESIONAL datos
    public Profesional crearProfesional(Long matriculaProfesional, String nombreProfesional, String apellidoProfesional,
            int edadProfesional,
            String especialidadProfesional, Long puntuacionProfesional,
            double precioConsulta, String caracteristicaDeOferta) {

        Profesional profesional = new Profesional();
        profesional.setMatriculaProfesional(matriculaProfesional);
        profesional.setNombreProfesional(nombreProfesional);
        profesional.setApellidoProfesional(apellidoProfesional);
        profesional.setEdadProfesional(edadProfesional);
        profesional.setEspecialidadProfesional(especialidadProfesional);
        profesional.setPuntuacionProfesional(puntuacionProfesional);
        // profesional.setHorariosDisponibles(horariosDisponibles); // Agregar ARRIBA!
        profesional.setPrecioConsulta(precioConsulta);
        profesional.setCaracteristicaDeOferta(caracteristicaDeOferta);//(telemedicina, presencial, ubicación, obras sociales, datos de contacto).


        return profesionalRepositorio.save(profesional);
    }

    // ACTUALIZAR DATOS DE UN PROFESIONAL
    public void actualizarDatosProfesional(Long id, Long matriculaProfesional, String nombreProfesional,
            String apellidoProfesional,
            int edadProfesional,
            String especialidadProfesional, Long puntuacionProfesional,
            double precioConsulta) {

        Optional<Profesional> respuesta = profesionalRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Profesional profesional = new Profesional();
            profesional.setMatriculaProfesional(matriculaProfesional);
            profesional.setNombreProfesional(nombreProfesional);
            profesional.setApellidoProfesional(apellidoProfesional);
            profesional.setEdadProfesional(edadProfesional);
            profesional.setEspecialidadProfesional(especialidadProfesional);
            profesional.setPuntuacionProfesional(puntuacionProfesional);
            // profesional.setHorariosDisponibles(horariosDisponibles); // Agregar ARRIBA!
            profesional.setPrecioConsulta(precioConsulta);
            profesionalRepositorio.save(profesional);

        }

    }

    // LISTAR PROFESIONALES
    public List<Profesional> listaProfesionales() {

        return (List<Profesional>) profesionalRepositorio.findAll();
    }

    // GUARDAR PROFESIONALES
    public void guardar(Profesional profesional) {

        profesionalRepositorio.save(profesional);
    }

    /*
     * // DAR DE BAJA PROFESIONAL /ELIMINAR PROFESIONALES // SE ELIMINAN EN EL SERVICIO DE USUARIO POR LOS ROLES
     * public void bajaDeProfesional(Long id) {
     * Optional<Profesional> validacion = profesionalRepositorio.findById(id);
     * if (validacion.isPresent()) {
     * Profesional profesional = validacion.get();
     * profesional.setEstadoProfesional(false);
     * profesionalRepositorio.save(profesional);
     * }
     * }
     */

    // BUSCAR PROFESIONAL POR ID
    public Profesional buscarProfesional(Long matricula) {

        Optional<Profesional> respuesta = profesionalRepositorio.findByMatriculaProfesional(matricula);

        Profesional profesional = respuesta.get();
        return profesional;
    }


    
   
}
