package com.MiSaludDigital.ServicioSalud.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioControlador {

//VISTA INICIAL DE LA PAGINA    
@GetMapping("/inicio")    
public String inicio(){




    return "index.html";
}



}