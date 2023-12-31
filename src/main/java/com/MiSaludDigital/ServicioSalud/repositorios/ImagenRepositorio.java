package com.MiSaludDigital.ServicioSalud.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiSaludDigital.ServicioSalud.entidades.Imagen;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Long>{

    Optional<Imagen> findById(Long idImagen);

}
