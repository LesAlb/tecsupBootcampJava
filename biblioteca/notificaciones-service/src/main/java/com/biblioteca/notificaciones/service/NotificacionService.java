package com.biblioteca.notificaciones.service;

import com.biblioteca.notificaciones.dto.NotificacionRequest;
import com.biblioteca.notificaciones.entity.Notificacion;
import com.biblioteca.notificaciones.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public Notificacion enviar(NotificacionRequest request) {
        Notificacion notificacion = new Notificacion();
        notificacion.setDestino(request.destino());
        notificacion.setMensaje(request.mensaje());
        notificacion.setCanal("EMAIL");
        notificacion.setEstado("ENVIADO");
        notificacion.setFechaEnvio(LocalDateTime.now());

        log.info("Notificación simulada enviada a {}: {}", request.destino(), request.mensaje());

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }
}



//gestiona el envio y registro de notificaciones.

//registra nuevas notificaciones.
//consulta notificaciones por prestamo o ID.
//lista todas las notificaciones pendientes.
//marca notificaciones como enviadas.
