package com.MiSaludDigital.ServicioSalud.servicios;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.entidades.Paciente;
import com.MiSaludDigital.ServicioSalud.repositorios.PacienteRepositorio;

@Service
public class PacienteServicio {
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    
    public Paciente crearPaciente(Long dniPaciente,String nombrePaciente,String ApellidoPaciente,Date fechaNacimientoPaciente,String obraSocial,Double telContacto,String intencionConsulta){

        Paciente paciente = new Paciente();
        paciente.setDniPaciente(dniPaciente);
        paciente.setNombrePaciente(nombrePaciente);
        paciente.setApellidoPaciente(ApellidoPaciente);
        paciente.setFechaNacimientoPaciente(fechaNacimientoPaciente);
        paciente.setObraSocial(obraSocial);
        paciente.setTelContacto(telContacto);
        paciente.setIntencionConsulta(intencionConsulta);
        
        return pacienteRepositorio.save(paciente);
    }
}
