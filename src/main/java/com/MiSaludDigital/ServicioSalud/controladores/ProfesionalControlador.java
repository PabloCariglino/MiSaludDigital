package com.MiSaludDigital.ServicioSalud.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MiSaludDigital.ServicioSalud.servicios.ProfesionalServicio;

@Controller
@RequestMapping("/profesional")
public class ProfesionalControlador {
    @Autowired
    private ProfesionalServicio profesionalServicio;

    // VISTA INICIO DEL PROFESIONAL
    @GetMapping("/inicioProfesional")
    public String vistaProfesional() {

        return "vistaProfesional.html";
    }

    // LISTADO DE TURNOS QUE POSEE EL PROFESIONAL
    @GetMapping("/listadoDeTurnos")
    public String listadoDeTurnos() {

        return "profesional/lista_turnosProfesional.html";
    }

    // LISTADO DE PACIENTES QUE POSEE EL PROFESIONAL
    @GetMapping("/listadoPacientes")
    public String listadoPacientes() {

        return "profesional/lista_pacientes";
    }

    //DATOS DEL PROFESIONAL
    @GetMapping("/datos")
    public String datosProfesional(){

        return"/profesional/datos_profesional.html";
    }

}
