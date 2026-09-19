package com.biblioteca.libros.repository;

import com.biblioteca.libros.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, String> {
}
