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

    

    @GetMapping("/listadoDeTurnos")
    public String listadoDeTurnos(){

        return"profesional/lista_turnosProfesional.html";
    }

    @GetMapping("/listadoPacientes")
    public String listadoPacientes() {

        return"profesional/lista_pacientes";
    }
    

     // VISTA DEL PROFESIONAL
     @GetMapping("/inicioProfesional")
     public String vistaProfesional() {
 
         return "vistaProfesional.html";
     }


}
