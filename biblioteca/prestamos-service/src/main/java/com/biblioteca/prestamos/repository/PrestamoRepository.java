package com.biblioteca.prestamos.repository;

import com.biblioteca.prestamos.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}

//guarda, actualiza y elimina prestamos.
//consulta prestamos por socio.
//consulta prestamos por ejemplar.
//lista prestamos pendientes de devolucion.