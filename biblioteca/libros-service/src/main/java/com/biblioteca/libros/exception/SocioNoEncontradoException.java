package com.biblioteca.libros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SocioNoEncontradoException extends RuntimeException {
    public SocioNoEncontradoException(String codigoSocio) {
        super("No se encontró el socio con código: " + codigoSocio);
    }
}
