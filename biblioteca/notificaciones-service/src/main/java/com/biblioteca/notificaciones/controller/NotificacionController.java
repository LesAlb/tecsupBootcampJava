package com.biblioteca.notificaciones.controller;

import com.biblioteca.notificaciones.dto.NotificacionRequest;
import com.biblioteca.notificaciones.entity.Notificacion;
import com.biblioteca.notificaciones.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public Notificacion recibir(@RequestBody NotificacionRequest request) {
        return notificacionService.enviar(request);
    }

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listar();
    }
}



//registra notificaciones  POST /notificaciones.
//lista todas las notificaciones  GET /notificaciones.
//busca notificaciones por ID  GET /notificaciones/{id}.
//busca notificaciones por préstamo  GET /notificaciones/prestamo/{prestamoId}.
//lista notificaciones pendientes  GET /notificaciones/pendientes.
//marca notificaciones como enviadas  PUT /notificaciones/{id}/enviar.