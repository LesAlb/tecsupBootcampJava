package com.biblioteca.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(Long id) {
        super("No se encontró el préstamo con id: " + id);
    }
}
