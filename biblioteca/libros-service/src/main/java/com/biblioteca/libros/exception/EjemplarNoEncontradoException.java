package com.biblioteca.libros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EjemplarNoEncontradoException extends RuntimeException {
    public EjemplarNoEncontradoException(String codigoEjemplar) {
        super("No se encontró el ejemplar con código: " + codigoEjemplar);
    }
}
