package com.biblioteca.libros.service;

import com.biblioteca.libros.entity.Socio;
import com.biblioteca.libros.exception.SocioNoEncontradoException;
import com.biblioteca.libros.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public Socio crear(Socio socio) {
        return socioRepository.save(socio);
    }

    public List<Socio> listar() {
        return socioRepository.findAll();
    }

    public Socio buscarPorCodigo(String codigoSocio) {
        return socioRepository.findById(codigoSocio)
                .orElseThrow(() -> new SocioNoEncontradoException(codigoSocio));
    }

    public Socio actualizar(String codigoSocio, Socio datos) {
        Socio existente = buscarPorCodigo(codigoSocio);
        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setActivo(datos.isActivo());
        return socioRepository.save(existente);
    }

    public void eliminar(String codigoSocio) {
        Socio existente = buscarPorCodigo(codigoSocio);
        socioRepository.delete(existente);
    }
}

// cuando guarda un nuevo Socio en bd:
// se inserta con un id autoincremental.
// fechaCreacion se llena automáticamente.
// fechaActualizacion se actualiza cada vez que se modifique.
