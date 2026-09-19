package com.biblioteca.libros.controller;

import com.biblioteca.libros.entity.Socio;
import com.biblioteca.libros.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/socios")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Socio crear(@RequestBody Socio socio) {
        return socioService.crear(socio);
    }

    @GetMapping
    public List<Socio> listar() {
        return socioService.listar();
    }

    @GetMapping("/{codigoSocio}")
    public Socio buscar(@PathVariable String codigoSocio) {
        return socioService.buscarPorCodigo(codigoSocio);
    }

    @PutMapping("/{codigoSocio}")
    public Socio actualizar(@PathVariable String codigoSocio, @RequestBody Socio socio) {
        return socioService.actualizar(codigoSocio, socio);
    }

    @DeleteMapping("/{codigoSocio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String codigoSocio) {
        socioService.eliminar(codigoSocio);
    }
}


//@PostMapping , endpoint para registrar un nuevo socio.
//@GetMapping , endpoints para listar todos, buscar por ID, email y apellido.
//@DeleteMapping , endpoint para eliminar un socio por ID.
//ResponseEntity , envuelve las respuestas HTTP.