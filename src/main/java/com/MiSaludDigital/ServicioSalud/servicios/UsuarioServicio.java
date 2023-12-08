package com.MiSaludDigital.ServicioSalud.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.MiSaludDigital.ServicioSalud.entidades.Imagen;
import com.MiSaludDigital.ServicioSalud.entidades.Usuario;
import com.MiSaludDigital.ServicioSalud.enumeraciones.Rol;
import com.MiSaludDigital.ServicioSalud.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    // METODO PARA REGISTRO CREACION DE USUARIO
    @Transactional
    public void registrarUsuario(String nombreUsuario, String email, String password, String password2,
            MultipartFile archivo)
            throws Exception {

        validar(nombreUsuario, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombreUsuario);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));

        if (email.equals("admin@admin.com")) {
            usuario.setRol(Rol.ADMIN);

        } else if (email.endsWith("@profesional.com")) {
            // Verificar si el usuario actual tiene el rol de ADMIN
            if (usuario.getRol() == Rol.ADMIN) {
                usuario.setRol(Rol.PROFESIONAL);
            } else {
                throw new IllegalStateException("Solo el rol ADMIN puede registrar profesionales");
            }

        } else {
            usuario.setRol(Rol.USER);
        }

        Imagen imagen = imagenServicio.guardar(archivo);

        usuario.setImagen(imagen);

        usuarioRepositorio.save(usuario);
    }

    // ACTUALIZAR EL PERFIL DEL USUARIO
    @Transactional
    public void actualizarUsuario(MultipartFile archivo, Long idUsuario, String nombreUsuario, String email,
            String password,
            String password2) throws Exception {

        validar(nombreUsuario, email, password, password2);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setEmail(email);

            usuario.setPassword(new BCryptPasswordEncoder().encode(password));

            // usuario.setRol(Rol.USER);

            Long idImagen = null;

            if (usuario.getImagen() != null) {
                idImagen = usuario.getImagen().getId();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

            usuario.setImagen(imagen);

            usuarioRepositorio.save(usuario);
        }

    }

    // DEVUELVE UN USUARIO POT ID
    public Usuario getOne(Long id) {
        return usuarioRepositorio.getOne(id);
    }

    // LISTAR Usuarios
    public List<Usuario> ListarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioRepositorio.findAll();
        return usuarios;

    }

    // ELIMINAR Usuarios
    /*
     * public void eliminarUsuario(Long id) {
     * Optional<Usuario> validacion = usuarioRepositorio.findById(id);
     * if (validacion.isPresent()) {
     * Usuario usuario = validacion.get();
     * usuario.setAlta(false);
     * usuarioRepositorio.save(usuario);
     * 
     * }
     * }
     */

    // VALIDACION PARA REGISTRO DE USUARIO
    private void validar(String nombre, String email, String password, String password2) throws Exception {

        if (nombre.isEmpty() || nombre == null) {
            throw new Exception("el nombre no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new Exception("el email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new Exception("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }

        if (!password.equals(password2)) {
            throw new Exception("Las contraseñas ingresadas deben ser iguales");
        }

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

    public Usuario getUsuarioConImagen(Long id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

}
