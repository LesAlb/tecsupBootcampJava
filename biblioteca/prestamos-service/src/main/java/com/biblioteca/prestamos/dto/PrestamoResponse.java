package com.biblioteca.prestamos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrestamoResponse {
    private Long id;
    private String codigoEjemplar;
    private String codigoSocio;
    private String estado;
    private String motivoRechazo;
    private LocalDateTime fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDateTime fechaDevolucionReal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoEjemplar() { return codigoEjemplar; }
    public void setCodigoEjemplar(String codigoEjemplar) { this.codigoEjemplar = codigoEjemplar; }
    public String getCodigoSocio() { return codigoSocio; }
    public void setCodigoSocio(String codigoSocio) { this.codigoSocio = codigoSocio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivoRechazo() { return motivoRechazo; }
    public void setMotivoRechazo(String motivoRechazo) { this.motivoRechazo = motivoRechazo; }
    public LocalDateTime getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDateTime fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public LocalDate getFechaDevolucionEsperada() { return fechaDevolucionEsperada; }
    public void setFechaDevolucionEsperada(LocalDate fechaDevolucionEsperada) { this.fechaDevolucionEsperada = fechaDevolucionEsperada; }
    public LocalDateTime getFechaDevolucionReal() { return fechaDevolucionReal; }
    public void setFechaDevolucionReal(LocalDateTime fechaDevolucionReal) { this.fechaDevolucionReal = fechaDevolucionReal; }
}
