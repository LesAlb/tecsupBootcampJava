package com.biblioteca.prestamos.controller;

import com.biblioteca.prestamos.dto.PrestamoRequest;
import com.biblioteca.prestamos.dto.PrestamoResponse;
import com.biblioteca.prestamos.entity.Prestamo;
import com.biblioteca.prestamos.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping
    public PrestamoResponse registrar(@RequestBody PrestamoRequest request) {
        return prestamoService.registrar(request);
    }

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listar();
    }

    @GetMapping("/{id}")
    public Prestamo buscar(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    @PostMapping("/{id}/devolucion")
    public PrestamoResponse devolver(@PathVariable Long id) {
        return prestamoService.devolver(id);
    }
}


//registra prestamos  POST /prestamos.
//lista todos los prestamos  GET /prestamos.
//busca prestamos por ID  GET /prestamos/{id}.
//busca prestamos por socio  GET /prestamos/socio/{socioId}.
//busca prestamos por ejemplar  GET /prestamos/ejemplar/{codigoEjemplar}.
//lista prestamos pendientes  GET /prestamos/pendientes.
//marca prestamos como devueltos  PUT /prestamos/{id}/devolver.