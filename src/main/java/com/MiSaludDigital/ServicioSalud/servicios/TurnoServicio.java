package com.MiSaludDigital.ServicioSalud.servicios;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.entidades.Paciente;
import com.MiSaludDigital.ServicioSalud.entidades.Profesional;
import com.MiSaludDigital.ServicioSalud.entidades.Turno;
import com.MiSaludDigital.ServicioSalud.repositorios.TurnoRepositorio;

@Service
public class TurnoServicio {

    @Autowired
    private TurnoRepositorio turnoRepositorio;

    @Autowired
    PacienteServicio pacienteServicio;

    @Autowired
    ProfesionalServicio profesionalServicio;

    public void reservarTurno(Long dniPaciente, Long profesionalId, Date fecha, LocalTime hora) {
        // Puedes agregar lógica de validación de disponibilidad de turnos aquí
       
        Paciente pc = pacienteServicio.buscarPacientePorDNI(dniPaciente);
        Profesional pf = profesionalServicio.buscarProfesional(profesionalId);
       
        Turno turno = new Turno();
        turno.setPaciente(pc); // Crea una instancia de Paciente con el ID
        turno.setProfesional(pf); // Crea una instancia de Profesional con el ID
        turno.setFecha(fecha);
        turno.setHora(hora);

        turnoRepositorio.save(turno);
    }

    // Obtener todos los turnos
    public List<Turno> obtenerTodosLosTurnos() {
        return turnoRepositorio.findAll();
    }

    // Obtener los turnos de un paciente específico
    public List<Turno> obtenerTurnosPorPaciente(Long pacienteId) {
        return turnoRepositorio.findByPacienteId(pacienteId);
    }

    // Obtener los turnos de un profesional específico
    public List<Turno> obtenerTurnosPorProfesional(Long profesionalId) {
        return turnoRepositorio.findByProfesionalId(profesionalId);
    }

    // Cancelar un turno
    public void cancelarTurno(Long turnoId) {
        // Puedes agregar lógica adicional, por ejemplo, verificar si el turno existe
        // antes de cancelarlo
        turnoRepositorio.deleteById(turnoId);
    }

    // Guardar un nuevo turno
    public void guardarTurno(Turno turno) {
        // Puedes agregar lógica de validación u otras operaciones antes de guardar el turno
        turnoRepositorio.save(turno);
    }

}
