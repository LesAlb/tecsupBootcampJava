package com.biblioteca.prestamos.dto;

public record PrestamoRequest(String codigoEjemplar, String codigoSocio, Integer dias) {
}
