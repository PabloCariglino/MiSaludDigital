package com.MiSaludDigital.ServicioSalud.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiSaludDigital.ServicioSalud.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{
    
Usuario findById = null;

   // @Query("SELECT u FROM Usuario u WHERE u.email = :email")
   // public Usuario buscarPorEmail(@Param("email") String email);

}
