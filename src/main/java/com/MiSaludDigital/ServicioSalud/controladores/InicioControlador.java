package com.MiSaludDigital.ServicioSalud.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.MiSaludDigital.ServicioSalud.entidades.Usuario;
import com.MiSaludDigital.ServicioSalud.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

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
    public String registro(@RequestParam String nombreUsuario, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo, MultipartFile archivo) {

        try {

            usuarioServicio.registrarUsuario(nombreUsuario, email, password, password2, archivo);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (Exception ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombreUsuario);
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

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/actualizarPerfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "actualizar_usuario.html";
    }

    // ACTUALIZAR USUARIO
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/actualizarPerfil/{idUsuario}")
    public String actualizarUsuario(
            MultipartFile archivo,
            @PathVariable Long idUsuario,
            @RequestParam String nombreUsuario,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2,
            ModelMap modelo) {

        try {
            usuarioServicio.actualizarUsuario(archivo, idUsuario, nombreUsuario, email, password, password2);

            modelo.put("exito", "Usuario actualizado correctamente!");

            return "index.html";
        } catch (Exception ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombreUsuario);
            modelo.put("email", email);

            return "actualizar_usuario.html";
        }

    }

}