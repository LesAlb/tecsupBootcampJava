package com.biblioteca.libros.service;

import com.biblioteca.libros.entity.Ejemplar;
import com.biblioteca.libros.exception.EjemplarNoEncontradoException;
import com.biblioteca.libros.repository.EjemplarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EjemplarServiceTest {

    @Mock
    private EjemplarRepository ejemplarRepository;

    @InjectMocks
    private EjemplarService ejemplarService;

    @Test
    void debeEncontrarEjemplarExistente() {
        Ejemplar ejemplar = new Ejemplar("BIB0001", "Titulo", "Autor", "ISBN", 2020, true, null, null);
        when(ejemplarRepository.findById("BIB0001")).thenReturn(Optional.of(ejemplar));

        Ejemplar resultado = ejemplarService.buscarPorCodigo("BIB0001");

        assertThat(resultado.getCodigoEjemplar()).isEqualTo("BIB0001");
    }

    @Test
    void debeLanzarExcepcionSiNoExiste() {
        when(ejemplarRepository.findById("NOEXISTE")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ejemplarService.buscarPorCodigo("NOEXISTE"))
                .isInstanceOf(EjemplarNoEncontradoException.class);
    }
}
