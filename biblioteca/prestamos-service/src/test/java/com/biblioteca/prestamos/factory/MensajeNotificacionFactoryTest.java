package com.biblioteca.prestamos.factory;

import com.biblioteca.prestamos.entity.Prestamo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MensajeNotificacionFactoryTest {

    private final MensajeNotificacionFactory factory = new MensajeNotificacionFactory();

    @Test
    void debeArmarMensajeParaRegistrado() {
        Prestamo prestamo = new Prestamo();
        prestamo.setCodigoEjemplar("BIB0001");
        prestamo.setEstado("REGISTRADA");
        prestamo.setFechaDevolucionEsperada(LocalDate.of(2026, 8, 1));

        String mensaje = factory.crearMensaje(prestamo);

        assertThat(mensaje).contains("BIB0001").contains("registrado");
    }

    @Test
    void debeArmarMensajeParaRechazado() {
        Prestamo prestamo = new Prestamo();
        prestamo.setCodigoEjemplar("BIB0003");
        prestamo.setEstado("RECHAZADA");
        prestamo.setMotivoRechazo("No disponible");

        String mensaje = factory.crearMensaje(prestamo);

        assertThat(mensaje).contains("No se pudo").contains("No disponible");
    }

    @Test
    void debeArmarMensajeParaDevuelto() {
        Prestamo prestamo = new Prestamo();
        prestamo.setCodigoEjemplar("BIB0002");
        prestamo.setEstado("DEVUELTO");

        String mensaje = factory.crearMensaje(prestamo);

        assertThat(mensaje).contains("Gracias por devolver");
    }
}
