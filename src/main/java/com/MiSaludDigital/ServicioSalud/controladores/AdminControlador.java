package com.MiSaludDigital.ServicioSalud.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.MiSaludDigital.ServicioSalud.servicios.ProfesionalServicio;
import com.MiSaludDigital.ServicioSalud.servicios.UsuarioServicio;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private ProfesionalServicio profesionalServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/adminDashboard")
    public String inicioAdmin() {

        return "/admin/vistaAdmin.html";
    }

    // REGISTRA DATOS DE UN PROFESIONAL
    @GetMapping("/registrarDatosProfesional")
    public String registrarProfesional() {
        return "admin/altaDatos_profesional.html";
    }

    @PostMapping("/registroDatosProfesional")
    public String registroProfesional(@RequestParam Long matriculaProfesional, @RequestParam String nombreProfesional,
            @RequestParam String apellidoProfesional, @RequestParam int edadProfesional,
            @RequestParam String especialidadProfesional, @RequestParam Long puntuacionProfesional,
            @RequestParam double precioConsulta, ModelMap modelo) throws Exception {

        try {
            profesionalServicio.crearProfesional(matriculaProfesional, nombreProfesional, apellidoProfesional,
                    edadProfesional, especialidadProfesional, puntuacionProfesional, precioConsulta);
            modelo.put("exito", "Profesional registrado con éxito");
            return "/index";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "admin/altaDatos_profesional.html";

    }

    // Registro de usuario profesional
    @GetMapping("/registrarUsuarioProfesional")
    public String registrarUsuario() {

        return "admin/altaUsuario_profesional.html";
    }

    @PostMapping("/registroUsuarioProfesional")
    public String registroUsuarioProfesinal(@RequestParam String nombreUsuario, @RequestParam String email,
            @RequestParam String password,
            String password2, ModelMap modelo, @RequestPart MultipartFile archivo) {

        try {

            usuarioServicio.registrarUsuario(nombreUsuario, email, password, password2, archivo);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (Exception ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombreUsuario);
            modelo.put("email", email);

            return "admin/altaUsuario_profesional.html";
        }

    }

    // LOGUEO DE USUARIO profesional
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

    // MUESTRA LISTA DE PROFESIONALES DEL ADMIN
    @GetMapping("/listadoProfesionalesAdmin")
    public String listadoProfesionalesAdmin() {

        return "admin/listaProfesionales_admin";
    }

    // MUESTRA LISTA DE PACIENTES DEL ADMIN
    @GetMapping("/listadoPacientesAdmin")
    public String listadoPacientesAdmin() {

        return "admin/listaPacientes_admin";
    }

}
