package com.biblioteca.libros.controller;

import com.biblioteca.libros.dto.DisponibilidadRequest;
import com.biblioteca.libros.entity.Ejemplar;
import com.biblioteca.libros.service.EjemplarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class EjemplarController {

    private final EjemplarService ejemplarService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ejemplar crear(@RequestBody Ejemplar ejemplar) {
        return ejemplarService.crear(ejemplar);
    }

    @GetMapping
    public List<Ejemplar> listar() {
        return ejemplarService.listar();
    }

    @GetMapping("/{codigoEjemplar}")
    public Ejemplar buscar(@PathVariable String codigoEjemplar) {
        return ejemplarService.buscarPorCodigo(codigoEjemplar);
    }

    @PutMapping("/{codigoEjemplar}")
    public Ejemplar actualizar(@PathVariable String codigoEjemplar, @RequestBody Ejemplar ejemplar) {
        return ejemplarService.actualizar(codigoEjemplar, ejemplar);
    }

    @DeleteMapping("/{codigoEjemplar}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String codigoEjemplar) {
        ejemplarService.eliminar(codigoEjemplar);
    }

    // Lo usa prestamos-service internamente (comunicación por nombre vía Eureka)
    @PatchMapping("/{codigoEjemplar}/disponibilidad")
    public Ejemplar cambiarDisponibilidad(@PathVariable String codigoEjemplar,
                                           @RequestBody DisponibilidadRequest request) {
        return ejemplarService.cambiarDisponibilidad(codigoEjemplar, request.disponible());
    }
}

// controller porque son la capa que expone la API REST.
