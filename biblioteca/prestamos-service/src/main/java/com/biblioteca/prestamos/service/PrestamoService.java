package com.biblioteca.prestamos.service;

import com.biblioteca.prestamos.builder.PrestamoResponseBuilder;
import com.biblioteca.prestamos.client.LibrosClient;
import com.biblioteca.prestamos.client.NotificacionesClient;
import com.biblioteca.prestamos.dto.EjemplarDTO;
import com.biblioteca.prestamos.dto.PrestamoRequest;
import com.biblioteca.prestamos.dto.PrestamoResponse;
import com.biblioteca.prestamos.dto.SocioDTO;
import com.biblioteca.prestamos.entity.Prestamo;
import com.biblioteca.prestamos.exception.PrestamoNoEncontradoException;
import com.biblioteca.prestamos.exception.PrestamoYaDevueltoException;
import com.biblioteca.prestamos.factory.MensajeNotificacionFactory;
import com.biblioteca.prestamos.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private static final int DIAS_PRESTAMO_DEFAULT = 7;

    private final PrestamoRepository prestamoRepository;
    private final LibrosClient librosClient;
    private final NotificacionesClient notificacionesClient;
    private final MensajeNotificacionFactory mensajeFactory;

    public PrestamoResponse registrar(PrestamoRequest request) {
        // 1) Validar socio
        Optional<SocioDTO> socioOpt = librosClient.buscarSocio(request.codigoSocio());
        if (socioOpt.isEmpty()) {
            return rechazar(request, "Socio no existe");
        }
        if (!socioOpt.get().activo()) {
            return rechazar(request, "Socio inactivo");
        }

        // 2) Validar ejemplar
        Optional<EjemplarDTO> ejemplarOpt = librosClient.buscarEjemplar(request.codigoEjemplar());
        if (ejemplarOpt.isEmpty()) {
            return rechazar(request, "Ejemplar no existe");
        }
        if (!ejemplarOpt.get().disponible()) {
            return rechazar(request, "No disponible");
        }

        // 3) Camino feliz: marcar el ejemplar como no disponible, registrar y notificar
        int dias = request.dias() != null ? request.dias() : DIAS_PRESTAMO_DEFAULT;

        Prestamo prestamo = new Prestamo();
        prestamo.setCodigoEjemplar(request.codigoEjemplar());
        prestamo.setCodigoSocio(request.codigoSocio());
        prestamo.setFechaPrestamo(LocalDateTime.now());
        prestamo.setFechaDevolucionEsperada(LocalDateTime.now().plusDays(dias).toLocalDate());
        prestamo.setEstado("REGISTRADA");

        librosClient.cambiarDisponibilidad(request.codigoEjemplar(), false);
        prestamo = prestamoRepository.save(prestamo);

        String mensaje = mensajeFactory.crearMensaje(prestamo);
        notificacionesClient.enviar(socioOpt.get().email(), mensaje);

        return PrestamoResponseBuilder.nuevo()
                .id(prestamo.getId())
                .codigoEjemplar(prestamo.getCodigoEjemplar())
                .codigoSocio(prestamo.getCodigoSocio())
                .estado(prestamo.getEstado())
                .fechaPrestamo(prestamo.getFechaPrestamo())
                .fechaDevolucionEsperada(prestamo.getFechaDevolucionEsperada())
                .build();
    }

    private PrestamoResponse rechazar(PrestamoRequest request, String motivo) {
        // Importante (sección 6.3): el flujo SE CORTA aquí. NO se llama a notificaciones-service,
        // y sobre todo, NO se responde con un error 500: se responde 200 OK con estado RECHAZADA.
        Prestamo prestamo = new Prestamo();
        prestamo.setCodigoEjemplar(request.codigoEjemplar());
        prestamo.setCodigoSocio(request.codigoSocio());
        prestamo.setEstado("RECHAZADA");
        prestamo.setMotivoRechazo(motivo);
        prestamo = prestamoRepository.save(prestamo);

        return PrestamoResponseBuilder.nuevo()
                .id(prestamo.getId())
                .codigoEjemplar(prestamo.getCodigoEjemplar())
                .codigoSocio(prestamo.getCodigoSocio())
                .estado(prestamo.getEstado())
                .motivoRechazo(prestamo.getMotivoRechazo())
                .build();
    }

    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));
    }

    public PrestamoResponse devolver(Long id) {
        Prestamo prestamo = buscarPorId(id);

        if ("DEVUELTO".equals(prestamo.getEstado())) {
            throw new PrestamoYaDevueltoException(id);
        }

        prestamo.setEstado("DEVUELTO");
        prestamo.setFechaDevolucionReal(LocalDateTime.now());
        prestamo = prestamoRepository.save(prestamo);

        librosClient.cambiarDisponibilidad(prestamo.getCodigoEjemplar(), true);

        String mensaje = mensajeFactory.crearMensaje(prestamo);
        notificacionesClient.enviar(prestamo.getCodigoSocio(), mensaje);

        return PrestamoResponseBuilder.nuevo()
                .id(prestamo.getId())
                .codigoEjemplar(prestamo.getCodigoEjemplar())
                .codigoSocio(prestamo.getCodigoSocio())
                .estado(prestamo.getEstado())
                .fechaDevolucionReal(prestamo.getFechaDevolucionReal())
                .build();
    }
}

// logica de negocio
//gestion de prestamos y se comunica con otros microservicios.

//registrar nuevos prestamos.
//consulta prestamos por socio o ejemplar.
//lista prestamos pendientes.
//marca prestamos como devueltos.

//cuando haces POST /prestamos en prestamos-service:
//se guarda el préstamo en la BD local.
// llama a libros-service para marcar el ejemplar como no disponible.
//el servicio devuelve el prestamo registrado.