package com.MiSaludDigital.ServicioSalud.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiSaludDigital.ServicioSalud.entidades.Usuario;
import com.MiSaludDigital.ServicioSalud.enumeraciones.Rol;
import com.MiSaludDigital.ServicioSalud.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    //@Autowired
    //private ImagenServicio imagenServicio;

    // METODO PARA REGISTRO DE USUARIO
    @Transactional
    public void registrarUsuario( String nombre, String email, String password, String password2)
        {

        // validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombre);
        usuario.setEmail(email);


        //usuario.setPassword(new BCryptPasswordEncoder().encode(password));

        usuario.setRol(Rol.ROL_USER);

        //Imagen imagen = imagenServicio.guardar(archivo);

        //usuario.setImagen(imagen);

        usuarioRepositorio.save(usuario);
    }

   
}
