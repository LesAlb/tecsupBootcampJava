package com.biblioteca.libros.repository;

import com.biblioteca.libros.entity.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, String> {
}
