package com.biblioteca.prestamos.service;

import com.biblioteca.prestamos.client.LibrosClient;
import com.biblioteca.prestamos.client.NotificacionesClient;
import com.biblioteca.prestamos.dto.EjemplarDTO;
import com.biblioteca.prestamos.dto.PrestamoRequest;
import com.biblioteca.prestamos.dto.PrestamoResponse;
import com.biblioteca.prestamos.dto.SocioDTO;
import com.biblioteca.prestamos.entity.Prestamo;
import com.biblioteca.prestamos.exception.PrestamoYaDevueltoException;
import com.biblioteca.prestamos.factory.MensajeNotificacionFactory;
import com.biblioteca.prestamos.repository.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceTest {

    @Mock private PrestamoRepository prestamoRepository;
    @Mock private LibrosClient librosClient;
    @Mock private NotificacionesClient notificacionesClient;

    private final MensajeNotificacionFactory mensajeFactory = new MensajeNotificacionFactory();

    private PrestamoService prestamoService;

    @BeforeEach
    void setUp() {
        prestamoService = new PrestamoService(prestamoRepository, librosClient, notificacionesClient, mensajeFactory);
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(inv -> {
            Prestamo p = inv.getArgument(0);
            if (p.getId() == null) p.setId(1L);
            return p;
        });
    }

    @Test
    void debeRegistrarPrestamoCuandoSocioActivoYEjemplarDisponible() {
        when(librosClient.buscarSocio("S001")).thenReturn(Optional.of(new SocioDTO("S001", "Ana", "ana@mail.com", true)));
        when(librosClient.buscarEjemplar("BIB0001")).thenReturn(Optional.of(new EjemplarDTO("BIB0001", "Titulo", true)));

        PrestamoResponse response = prestamoService.registrar(new PrestamoRequest("BIB0001", "S001", 7));

        assertThat(response.getEstado()).isEqualTo("REGISTRADA");
        verify(librosClient).cambiarDisponibilidad("BIB0001", false);
        verify(notificacionesClient).enviar(any(), any());
    }

    @Test
    void debeRechazarPorEjemplarNoDisponible() {
        when(librosClient.buscarSocio("S001")).thenReturn(Optional.of(new SocioDTO("S001", "Ana", "ana@mail.com", true)));
        when(librosClient.buscarEjemplar("BIB0003")).thenReturn(Optional.of(new EjemplarDTO("BIB0003", "Titulo", false)));

        PrestamoResponse response = prestamoService.registrar(new PrestamoRequest("BIB0003", "S001", 7));

        assertThat(response.getEstado()).isEqualTo("RECHAZADA");
        assertThat(response.getMotivoRechazo()).isEqualTo("No disponible");
        verify(notificacionesClient, never()).enviar(any(), any());
    }

    @Test
    void debeRechazarPorSocioInactivo() {
        when(librosClient.buscarSocio("S002")).thenReturn(Optional.of(new SocioDTO("S002", "Luis", "luis@mail.com", false)));

        PrestamoResponse response = prestamoService.registrar(new PrestamoRequest("BIB0001", "S002", 7));

        assertThat(response.getEstado()).isEqualTo("RECHAZADA");
        assertThat(response.getMotivoRechazo()).isEqualTo("Socio inactivo");
    }

    @Test
    void debeRechazarPorEjemplarInexistente() {
        when(librosClient.buscarSocio("S001")).thenReturn(Optional.of(new SocioDTO("S001", "Ana", "ana@mail.com", true)));
        when(librosClient.buscarEjemplar("NOEXISTE")).thenReturn(Optional.empty());

        PrestamoResponse response = prestamoService.registrar(new PrestamoRequest("NOEXISTE", "S001", 7));

        assertThat(response.getEstado()).isEqualTo("RECHAZADA");
        assertThat(response.getMotivoRechazo()).isEqualTo("Ejemplar no existe");
    }

    @Test
    void debeMarcarPrestamoComoDevuelto() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setCodigoEjemplar("BIB0001");
        prestamo.setCodigoSocio("S001");
        prestamo.setEstado("REGISTRADA");
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        PrestamoResponse response = prestamoService.devolver(1L);

        assertThat(response.getEstado()).isEqualTo("DEVUELTO");
        verify(librosClient).cambiarDisponibilidad("BIB0001", true);
    }

    @Test
    void debeRechazarDevolucionDuplicada() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setEstado("DEVUELTO");
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        assertThatThrownBy(() -> prestamoService.devolver(1L))
                .isInstanceOf(PrestamoYaDevueltoException.class);
    }
}
