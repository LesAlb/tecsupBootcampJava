package com.biblioteca.notificaciones.repository;

import com.biblioteca.notificaciones.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}

// guarda, actualiza y elimina notificaciones.
//consulta notificaciones por prestamo.
//lista notificaciones pendientes de envío.
//filtra notificaciones por tipo.