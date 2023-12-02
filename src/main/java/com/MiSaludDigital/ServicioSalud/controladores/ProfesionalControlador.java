package com.MiSaludDigital.ServicioSalud.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MiSaludDigital.ServicioSalud.servicios.ProfesionalServicio;

@Controller
@RequestMapping("/profesional")
public class ProfesionalControlador {
    @Autowired 
    private ProfesionalServicio profesionalServicio;

     // CREATE
    @GetMapping("/registrar")
    public String registrarProfesional() {
        return "profesional/profesional_form.html";
    }

    @PostMapping("/registro")
    public String registroProfesional(@RequestParam Long matriculaProfesional,@RequestParam String nombreProfesional,@RequestParam  String apellidoProfesional,@RequestParam  int edadProfesional,@RequestParam 
    String especialidadProfesional,@RequestParam  Long puntuacionProfesional,@RequestParam 
    double precioConsulta, ModelMap modelo) throws Exception {
        
        try {
            profesionalServicio.crearProfesional(matriculaProfesional,nombreProfesional, apellidoProfesional, edadProfesional, especialidadProfesional, puntuacionProfesional, precioConsulta);
            modelo.put("exito", "Profesional registrado con éxito");
            return "profesional/profesional_form.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "profesional/profesional_form.html";

    }

    @GetMapping("/listadoDeTurnos")
    public String listadoDeTurnos(){

        return"profesional/lista_turnosProfesional.html";
    }

    @GetMapping("/listadoPacientes")
    public String listadoPacientes() {

        return"profesional/lista_pacientes";
    }
    


}
