package com.MiSaludDigital.ServicioSalud.controladores;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MiSaludDigital.ServicioSalud.entidades.Usuario;
import com.MiSaludDigital.ServicioSalud.servicios.UsuarioServicio;

@Controller
@RequestMapping("/imagen")
public class imagenControlador {

    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable Long id) {
        Usuario usuario= usuarioServicio.getOne(id);
        byte[] imagenUsuario= usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagenUsuario, headers, HttpStatus.OK);
    }
}
