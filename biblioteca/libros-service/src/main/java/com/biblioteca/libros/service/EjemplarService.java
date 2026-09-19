package com.biblioteca.libros.service;

import com.biblioteca.libros.entity.Ejemplar;
import com.biblioteca.libros.exception.EjemplarNoEncontradoException;
import com.biblioteca.libros.repository.EjemplarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;

    public Ejemplar crear(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    public List<Ejemplar> listar() {
        return ejemplarRepository.findAll();
    }

    public Ejemplar buscarPorCodigo(String codigoEjemplar) {
        return ejemplarRepository.findById(codigoEjemplar)
                .orElseThrow(() -> new EjemplarNoEncontradoException(codigoEjemplar));
    }

    public Ejemplar actualizar(String codigoEjemplar, Ejemplar datos) {
        Ejemplar existente = buscarPorCodigo(codigoEjemplar);
        existente.setTitulo(datos.getTitulo());
        existente.setAutor(datos.getAutor());
        existente.setDisponible(datos.isDisponible());
        return ejemplarRepository.save(existente);
    }

    public void eliminar(String codigoEjemplar) {
        Ejemplar existente = buscarPorCodigo(codigoEjemplar);
        ejemplarRepository.delete(existente);
    }

    public Ejemplar cambiarDisponibilidad(String codigoEjemplar, boolean disponible) {
        Ejemplar existente = buscarPorCodigo(codigoEjemplar);
        existente.setDisponible(disponible);
        return ejemplarRepository.save(existente);
    }
}

// cuando guarda nuevo ejemplar en bd:
// se inserta con su codigoEjemplar como PK.
// fechaCreacion se llena automaticamente.
// fechaActualizacion se actualiza cada vez que se modifique.