package com.biblioteca.prestamos.builder;

import com.biblioteca.prestamos.dto.PrestamoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class PrestamoResponseBuilder {

    private final PrestamoResponse response = new PrestamoResponse();

    public static PrestamoResponseBuilder nuevo() {
        return new PrestamoResponseBuilder();
    }

    public PrestamoResponseBuilder id(Long id) {
        response.setId(id);
        return this;
    }

    public PrestamoResponseBuilder codigoEjemplar(String codigoEjemplar) {
        response.setCodigoEjemplar(codigoEjemplar);
        return this;
    }

    public PrestamoResponseBuilder codigoSocio(String codigoSocio) {
        response.setCodigoSocio(codigoSocio);
        return this;
    }

    public PrestamoResponseBuilder estado(String estado) {
        response.setEstado(estado);
        return this;
    }

    public PrestamoResponseBuilder motivoRechazo(String motivoRechazo) {
        response.setMotivoRechazo(motivoRechazo);
        return this;
    }

    public PrestamoResponseBuilder fechaPrestamo(LocalDateTime fechaPrestamo) {
        response.setFechaPrestamo(fechaPrestamo);
        return this;
    }

    public PrestamoResponseBuilder fechaDevolucionEsperada(LocalDate fecha) {
        response.setFechaDevolucionEsperada(fecha);
        return this;
    }

    public PrestamoResponseBuilder fechaDevolucionReal(LocalDateTime fecha) {
        response.setFechaDevolucionReal(fecha);
        return this;
    }

    public PrestamoResponse build() {
        return response;
    }
}
