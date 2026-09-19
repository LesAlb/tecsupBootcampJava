package com.biblioteca.prestamos.factory;

import com.biblioteca.prestamos.entity.Prestamo;
import org.springframework.stereotype.Component;

@Component
public class MensajeNotificacionFactory {

    public String crearMensaje(Prestamo prestamo) {
        return switch (prestamo.getEstado()) {
            case "REGISTRADA" -> crearMensajeRegistrado(prestamo);
            case "RECHAZADA" -> crearMensajeRechazado(prestamo);
            case "DEVUELTO" -> crearMensajeDevuelto(prestamo);
            default -> throw new IllegalArgumentException("Estado de préstamo no soportado: " + prestamo.getEstado());
        };
    }

    private String crearMensajeRegistrado(Prestamo prestamo) {
        return "Tu préstamo del ejemplar " + prestamo.getCodigoEjemplar() +
                " quedó registrado. Fecha límite de devolución: " + prestamo.getFechaDevolucionEsperada() + ".";
    }

    private String crearMensajeRechazado(Prestamo prestamo) {
        return "No se pudo registrar el préstamo del ejemplar " + prestamo.getCodigoEjemplar() +
                ". Motivo: " + prestamo.getMotivoRechazo() + ".";
    }

    private String crearMensajeDevuelto(Prestamo prestamo) {
        return "Gracias por devolver el ejemplar " + prestamo.getCodigoEjemplar() + ".";
    }
}
