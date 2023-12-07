package com.MiSaludDigital.ServicioSalud.controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MiSaludDigital.ServicioSalud.entidades.Profesional;
import com.MiSaludDigital.ServicioSalud.servicios.PacienteServicio;
import com.MiSaludDigital.ServicioSalud.servicios.ProfesionalServicio;

@Controller
@RequestMapping("/paciente")
public class PacienteControlador {
    @Autowired
    private PacienteServicio pacienteServicio;

    @Autowired
    private ProfesionalServicio profesionalServicio;

    // CREATE
    @GetMapping("/registrar")
    public String registrarPaciente() {
        return "paciente/paciente_form.html";
    }

    @PostMapping("/registro")
    public String registroPaciente(@RequestParam Long dniPaciente,@RequestParam String nombrePaciente,@RequestParam String ApellidoPaciente,@RequestParam String fechaNacimientoPaciente,@RequestParam String obraSocial,@RequestParam Double telContacto,@RequestParam String intencionConsulta, ModelMap modelo) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaDate = dateFormat.parse(fechaNacimientoPaciente);
            pacienteServicio.crearPaciente(dniPaciente, nombrePaciente,ApellidoPaciente, fechaDate, obraSocial, telContacto, intencionConsulta);
            modelo.put("exito", "Paciente registrado con éxito");
            return "paciente/paciente_form.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "paciente/paciente_form.html";

    }
    
    @GetMapping("/datos")
    public String datosPaciente(){

        return"/paciente/datos_paciente.html";
    }

    // VISTA PARA SELECCIONAR EL TURNO DEL PACIENTE
     @GetMapping("/selectTurno")
     public String sacarTurno() {
 
         return "/paciente/agendar_turno.html";
     }
 

     // VISTA DEL PACIENTE
     @GetMapping("/inicioPaciente")
     public String vistaPaciente(ModelMap modelo) {
        List<Profesional> profesionales = profesionalServicio.listaProfesionales();
        modelo.addAttribute("profesionales", profesionales);
 
         return "/paciente/vistaPaciente.html";
     }
 
}
