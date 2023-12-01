package com.MiSaludDigital.ServicioSalud.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.MiSaludDigital.ServicioSalud.servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class InicioControlador {

    // VISTA INICIAL DE LA PAGINA
    @GetMapping("/index")
    public String inicio() {

        return "index.html";
    }

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Registro de usuario
    @GetMapping("/registrar")
    public String registrarUsuario() {

        return "registro_usuario.html";
    }

    @PostMapping("/registroUsuario")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo, MultipartFile archivo) {

        try {
            // usuarioServicio.registrarPaciente(archivo, nombre, email, password,
            // password2);

            usuarioServicio.registrarUsuario(nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (Exception ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro_usuario.html";
        }

    }

    // LOGUEO DE USUARIO
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña inválidos");
            return "login.html";
        } else {
            modelo.put("exito", "Bienvenido");
            return "login.html";
        }
    }

  
   
    
    // VISTA DEL PROFESIONAL
    @GetMapping("/inicioProfesional")
    public String vistaProfesional() {

        return "vistaProfesional.html";
    }

  
   
}