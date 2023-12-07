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
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired 
    private ProfesionalServicio profesionalServicio;


    @GetMapping("/adminDashboard")
    public String inicioAdmin() {

        return "/admin/vistaAdmin.html";
    }

     // CREATE
    @GetMapping("/registrarDatosProfesional")
    public String registrarProfesional() {
        return "admin/profesional_form.html";
    }

    @PostMapping("/registroDatosProfesional")
    public String registroProfesional(@RequestParam Long matriculaProfesional,@RequestParam String nombreProfesional,@RequestParam  String apellidoProfesional,@RequestParam  int edadProfesional,@RequestParam 
    String especialidadProfesional,@RequestParam  Long puntuacionProfesional,@RequestParam 
    double precioConsulta, ModelMap modelo) throws Exception {
        
        try {
            profesionalServicio.crearProfesional(matriculaProfesional,nombreProfesional, apellidoProfesional, edadProfesional, especialidadProfesional, puntuacionProfesional, precioConsulta);
            modelo.put("exito", "Profesional registrado con éxito");
            return "admin/profesional_form.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "admin/profesional_form.html";

    }
}
