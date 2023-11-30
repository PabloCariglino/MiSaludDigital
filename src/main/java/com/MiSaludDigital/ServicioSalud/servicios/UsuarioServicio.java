package com.MiSaludDigital.ServicioSalud.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.MiSaludDigital.ServicioSalud.entidades.Usuario;
import com.MiSaludDigital.ServicioSalud.enumeraciones.Rol;
import com.MiSaludDigital.ServicioSalud.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // @Autowired
    // private ImagenServicio imagenServicio;

    // METODO PARA REGISTRO DE USUARIO
    @Transactional
    public void registrarUsuario(String nombre, String email, String password, String password2) {

        // validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombre);
        usuario.setEmail(email);

        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        if (email.equals("admin@admin.com")) {
            usuario.setRol(Rol.ADMIN);
        }else{
        usuario.setRol(Rol.USER);
        }

        

        // Imagen imagen = imagenServicio.guardar(archivo);

        // usuario.setImagen(imagen);

        usuarioRepositorio.save(usuario);
    }
    // El servicio debe implementar ↓
    // implements UserDetailsService

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email); // ←MODIFICAR
        if (usuario != null) { // ←MODIFICAR

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority perm = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(perm);

            // Luego de validar el usuario guardamos una sesion web
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            // La session contiene los datos del usuario recuperado de la base de datos
            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos); // ←MODIFICAR
        } else {
            return null;
        }
    }

}
