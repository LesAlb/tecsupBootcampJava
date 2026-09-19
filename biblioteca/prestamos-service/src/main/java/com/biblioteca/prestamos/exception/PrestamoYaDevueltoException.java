package com.biblioteca.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class PrestamoYaDevueltoException extends RuntimeException {
    public PrestamoYaDevueltoException(Long id) {
        super("El préstamo " + id + " ya fue devuelto anteriormente.");
    }
}
